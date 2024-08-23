# Gateway module

```shell
kubectl apply -f istiolab/gateway/deploy/secrets.yaml

./gradlew :istiolab:gateway:build

docker build -t dgcd/reference-snapshot:gateway -f ./istiolab/gateway/deploy/Dockerfile ./istiolab/gateway && \
docker push     dgcd/reference-snapshot:gateway

helm upgrade \
  --debug \
  --install kubelab-gateway istiolab/gateway/deploy/helm-chart/ \
  --set image.digest=3fc5df3b31b2858bc987deacdbdf63e9480dffb834f1c2b4226224cf86e5419d \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
