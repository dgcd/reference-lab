FROM dgcd/reference-snapshot:java-runtime-23

WORKDIR /kubelab/gateway

COPY ./build/libs/kubelab-gateway.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "kubelab-gateway.jar"]
