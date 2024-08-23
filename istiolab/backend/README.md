# Backend module

```shell
./gradlew :istiolab:backend:build

docker build -t dgcd/reference-snapshot:backend -f ./istiolab/backend/deploy/Dockerfile ./istiolab/backend && \
docker push     dgcd/reference-snapshot:backend

helm upgrade \
  --debug \
  --install kubelab-backend istiolab/backend/deploy/helm-chart/ \
  --set image.digest=29396ba48f6b90b00c2356a8ba7c85f51723d9ec8a6c1f9a17007c53382e57dc \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
