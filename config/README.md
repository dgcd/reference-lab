# Config server

```shell
# local check
curl http://##:##@localhost:8080/reference-app/dev

# build
./gradlew :config:build

docker build -t dgcd/reference-snapshot:config-4 -f ./config/deploy/Dockerfile ./config && \
docker push dgcd/reference-snapshot:config-4

helm upgrade \
  --debug \
  --install reference-config config/deploy/helm-chart/ \
  --set image.digest=32f3610d83c87fc3077ff798d6929a2ee91c6336f8e4a09dbd12f37220259eb2 \
  --set global.namespace=reference-config \
  --namespace reference-config
```

