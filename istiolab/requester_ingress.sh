#!/bin/bash

export CLUSTER_HOST=kubelab.lab.example.com && \
export CLUSTER_PORT=80 && \
printf "address: $CLUSTER_HOST:$CLUSTER_PORT\n"

curl http://$CLUSTER_HOST:$CLUSTER_PORT/node -H "Host: kubelab.lab.example.com" && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/nnode -H "Host: kubelab.lab.example.com" && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/api -s -X POST \
    -H "Content-Type: application/json" \
    -H "Host: kubelab.lab.example.com" \
    -d '{"requestName": "ingress_req_42", "requestId": 42}' | jq && \
curl http://$CLUSTER_HOST:$CLUSTER_PORT/test -H "Host: kubelab.lab.example.com" && \
printf "\n\n"
