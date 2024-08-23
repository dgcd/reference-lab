# Frontend module (Nginx + Vue.js)

```shell
# build front

docker build -t dgcd/reference-snapshot:frontend -f ./istiolab/frontend/deploy/Dockerfile ./istiolab/frontend && \
docker push     dgcd/reference-snapshot:frontend

helm upgrade \
  --debug \
  --install kubelab-frontend istiolab/frontend/deploy/helm-chart/ \
  --set image.digest=3ada58df0a09d7ef24325cdaec22c6861aea173e31129609fbfa497620e8f046 \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```
