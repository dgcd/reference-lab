# Reference Lab setup

## Prerequisites

- Check root README.md
- Build `java-runtime` image

## Setup lab2 (k8s)

- install clean Ubuntu Server with ip: `192.168.88.42`
- setup `MicroK8s` from `studies -> linux -> ubuntu-docker.sh`
- execute local:

```shell
echo "192.168.88.42 lab2" | sudo tee -a /etc/hosts && \
mkdir -p ~/.kube && \
scp lab2:~/.kube/config ~/.kube/config
```

## Setup lab1 (gitlab, minio)

- install clean Ubuntu Server with ip: `192.168.88.41`
- setup `Docker` from `studies -> linux -> ubuntu-docker.sh`
- make dirs:

```shell
mkdir -p reference_lab/postgres && \
mkdir -p reference_lab/gitlab/config && \
mkdir -p reference_lab/gitlab/data && \
mkdir -p reference_lab/gitlab/logs && \
mkdir -p reference_lab/minio
```

- execute in local repo:

```shell
echo "192.168.88.41 lab1" | sudo tee -a /etc/hosts && \
echo "192.168.88.41 gitlab.example.com" | sudo tee -a /etc/hosts && \
scp lab_environment/docker-compose.yaml lab1:~/reference_lab/
```

- gitlab configuration
    - username: root
    - password: `docker exec -it gitlab grep 'Password:' /etc/gitlab/initial_root_password`
    - change password, add ssh key
    - create `lab` group and add ci/cd variables:
        - DOCKER_REPO_USER
        - DOCKER_REPO_PASS
        - DOCKER_REPO_HOST - https://index.docker.io/v1/
        - K8S_AUTH_FILE
- create blank project `reference-lab` and push sources
    - for remote: `ssh://git@gitlab.example.com:2222/lab/reference-lab`

## Setup Minio for caching

- MinIO https://www.howtogeek.com/devops/how-to-configure-minio-as-a-shared-cache-for-gitlab-ci/
- defaults: `minioadmin/minioadmin`
- create bucket `gitlab` and user: `gitlabuser/gitlabpassword` with `read/write`

## Setup Gitlab runner

- config
  CoreDNS: [static DNS to kubernetes for gitlab](https://stackoverflow.com/questions/37166822/is-there-a-way-to-add-arbitrary-records-to-kube-dns):

```shell
kubectl edit cm coredns -n kube-system
```

```yaml
#@formatter:off
data:
  Corefile: |
    .:53 {
      errors
      health {
        lameduck 5s
      }
      hosts /etc/coredns/customdomains.db example.com {
        fallthrough
      }
      ready
      log . {
        class error
      }
      kubernetes cluster.local in-addr.arpa ip6.arpa {
        pods insecure
        fallthrough in-addr.arpa ip6.arpa
      }
      prometheus :9153
      forward . 8.8.8.8 8.8.4.4
      cache 30
      loop
      reload
      loadbalance
    }
  customdomains.db: |
    192.168.88.41 gitlab.example.com
    192.168.88.41 minio.example.com
    192.168.88.41 postgres.example.com
#@formatter:on
```

```shell
kubectl edit -n kube-system deployment coredns
```

```yaml
#@formatter:off
volumes:
  - name: config-volume
    configMap:
      name: coredns
      items:
        - key: Corefile
          path: Corefile
        - key: customdomains.db
          path: customdomains.db
#@formatter:on
```

```shell
kubectl rollout restart -n kube-system deployment/coredns
```

- install runner: [kubernetes](https://docs.gitlab.com/runner/install/kubernetes.html):

```shell
helm repo add gitlab https://charts.gitlab.io && \
helm repo update gitlab && \
helm search repo -l gitlab/gitlab-runner

helm install \
    --namespace gitlab-runner-ns \
    --create-namespace \
    -f ./lab_environment/runner-values.yaml \
    gitlab-runner-1 \
    gitlab/gitlab-runner
```

## Deploy reference lab

- apply `namespace/secret` from `app/deploy`, `config/deploy` and below config for
    - reference-dev
    - reference-test
    - reference-prod
    - reference-config

```shell
kubectl create secret docker-registry regcred \
  --docker-server=https://index.docker.io/v2/ \
  --docker-username=#### \
  --docker-password=######## \
  -n reference-#### && \
kubectl patch serviceaccount default \
  -p '{"imagePullSecrets": [{"name": "regcred"}]}' \
  -n reference-####

kubectl get secret regcred -n reference-#### --output=yaml && \
kubectl get secret regcred -n reference-#### --output="jsonpath={.data.\.dockerconfigjson}" | base64 -d && \
kubectl get serviceaccount default -n reference-#### --output=yaml
```

- create `reference-config-repo` repo with `cfg` and `.gitignore`
- create gitlab user `git_config_user/d9Hsn3HJ4m`
- config/README.md

```shell
echo "192.168.88.42 dev.lab.example.com"    | sudo tee -a /etc/hosts && \
echo "192.168.88.42 test.lab.example.com"   | sudo tee -a /etc/hosts && \
echo "192.168.88.42 prod.lab.example.com"   | sudo tee -a /etc/hosts && \
echo "192.168.88.42 config.lab.example.com" | sudo tee -a /etc/hosts
```

## Deploy istio lab

- [README.md](../istiolab/README.md)
