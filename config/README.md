# Config server

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
  --set image.digest=fa73728f4962a460f3a646923399c39c5da49c8bda2dd88e242edc1887f95efb \
  --set global.namespace=reference-config \
  --namespace reference-config
```
