- install docker and docker-compose to server #1 [192.168.88.41] (manually)
- install microk8s to server #2 [192.168.88.42] (from list)
- apply docker-compose.yaml for [gitlab](https://docs.gitlab.com/ee/install/docker.html) and nexus
- nexus credentials
    - username: admin
    - password: `docker exec -it nexus cat /nexus-data/admin.password && echo`
- gitlab credentials
    - username: root
    - password: `docker exec -it gitlab grep 'Password:' /etc/gitlab/initial_root_password`
    - for clone: `git clone ssh://git@gitlab.example.com:2222/lab/reference-lab`
- add [static DNS to kubernetes for gitlab](https://stackoverflow.com/questions/37166822/is-there-a-way-to-add-arbitrary-records-to-kube-dns)

```yaml
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
### try without this first, remove this comment
    catfact.ninja:443 {
        errors
        cache 30
        forward . 192.168.88.1
        reload
    }
  customdomains.db: |
    192.168.88.41 gitlab.example.com
```

- install runner to [kubernetes](https://docs.gitlab.com/runner/install/kubernetes.html):

```shell
helm install \
    --namespace gitlab-runner-ns \
    --create-namespace \
    --set gitlabUrl=http://gitlab.example.com,runnerRegistrationToken=token \
    gitlab-runner-lab2-2 \
    gitlab/gitlab-runner
```

- k8s

```shell
kubectl create secret docker-registry regcred \
  --docker-server=https://index.docker.io/v2/ \
  --docker-username=#### \
  --docker-password=######## \
  -n reference-dev

kubectl create secret docker-registry regcred \
  --docker-server=https://192.168.88.41:1112 \
  --docker-username=nexus_user \
  --docker-password=##### \
  -n reference-dev

kubectl get secret regcred -n reference-dev --output=yaml

kubectl get secret regcred -n reference-dev --output="jsonpath={.data.\.dockerconfigjson}" | base64 --decode

kubectl patch serviceaccount default \
  -p '{"imagePullSecrets": [{"name": "regcred"}]}' \
  -n reference-dev

kubectl get serviceaccount default -n reference-dev --output=yaml
```

- MinIO

https://www.howtogeek.com/devops/how-to-configure-minio-as-a-shared-cache-for-gitlab-ci/
