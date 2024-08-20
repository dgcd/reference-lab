# Simple server on Node.js

```shell
docker build -t dgcd/reference-snapshot:nodesrv -f ./nodesrv/deploy/Dockerfile ./nodesrv && \
docker push     dgcd/reference-snapshot:nodesrv

helm upgrade \
  --debug \
  --install kubelab-nodesrv nodesrv/deploy/helm-chart/ \
  --set image.digest=9752ae26665607c65afe13a69a9c3081d9a8e1738e8ddc2b2f9f4ccbc4d1b8fb \
  --set global.namespace=kubelab-dev \
  --namespace kubelab-dev
```

