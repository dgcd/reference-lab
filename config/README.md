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
  --set image.digest=3642d458f5b71c7efdd7ff633eb2c47eec303071c24b4ecbdcdba5216609029e \
  --set global.namespace=reference-config \
  --namespace reference-config
```
