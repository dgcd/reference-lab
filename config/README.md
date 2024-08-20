# Config server

### deploy

```shell
# local check
curl http://config_user:config_password@localhost:8080/reference-app/dev
# remote check
curl http://config_user:config_password@config.lab.example.com/reference-app/dev

# build
./gradlew :config:build

docker build -t dgcd/reference-snapshot:config -f ./config/deploy/Dockerfile ./config && \
docker push     dgcd/reference-snapshot:config

helm upgrade \
  --debug \
  --install reference-config config/deploy/helm-chart/ \
  --set image.digest=4a8ca08632a4c8a65c7febdaac580fb4a2619ab5f48b89b8342a38ce34c99979 \
  --set global.namespace=reference-config \
  --namespace reference-config
```
