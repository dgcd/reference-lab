# Config server

### env

- `GIT_USERNAME=dummy;GIT_PASSWORD=dummy;CONFIG_USERNAME=dummy;CONFIG_PASSWORD=dummy`

### deploy

```shell
# local check
curl http://##:##@localhost:8080/reference-app/dev

# build
./gradlew :config:build

docker build -t dgcd/reference-snapshot:config -f ./config/deploy/Dockerfile ./config && \
docker push     dgcd/reference-snapshot:config

helm upgrade \
  --debug \
  --install reference-config config/deploy/helm-chart/ \
  --set image.digest=86516b6d9c35b373a5cf5b084b1489b3e833324cf37741eb443a6be1a51916ac \
  --set global.namespace=reference-config \
  --namespace reference-config
```
