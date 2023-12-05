# loaded: service for load testing

```shell
# build
./gradlew :loaded:build

docker build -t dgcd/reference-snapshot:loaded -f ./loaded/deploy/Dockerfile ./loaded && \
docker push     dgcd/reference-snapshot:loaded

helm upgrade \
  --debug \
  --install reference-loaded loaded/deploy/helm-chart/ \
  --set image.digest=aaaaa \
  --set global.namespace=reference-loaded \
  --namespace reference-loaded
```
