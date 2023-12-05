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
  --set image.digest=51abd79ff4f36ecfdd375d5e6812e112cf4ba9d4b10b7810a7b12f0ea1cba5f3 \
  --set global.namespace=reference-config \
  --namespace reference-config
```
