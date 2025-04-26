FROM dgcd/reference-snapshot:java-runtime-24

WORKDIR /kubelab/gateway

COPY ./build/libs/kubelab-gateway.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "kubelab-gateway.jar"]
