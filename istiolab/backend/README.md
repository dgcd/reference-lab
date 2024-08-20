# Backend module

```shell
../gradlew :istiolab:backend:build

docker build -t dgcd/reference-snapshot:backend -f ./backend/deploy/Dockerfile ./backend && \
docker push     dgcd/reference-snapshot:backend

helm upgrade \
  --debug \
  --install kubelab-backend backend/deploy/helm-chart/ \
  --set image.digest=1d39b1397b54b2d17447beb528abb4e24bd812b93da68c6cffd8d8ee99b1d0a4 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
