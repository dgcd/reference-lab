# Gateway module

```shell
kubectl apply -f gateway/deploy/secrets.yaml

../gradlew :istiolab:gateway:build

docker build -t dgcd/reference-snapshot:gateway -f ./gateway/deploy/Dockerfile ./gateway && \
docker push     dgcd/reference-snapshot:gateway

helm upgrade \
  --debug \
  --install kubelab-gateway gateway/deploy/helm-chart/ \
  --set image.digest=0b6929b65db0cd505edee65b3998ca47eb62f0cacfab48bd80bac80b18017a24 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
