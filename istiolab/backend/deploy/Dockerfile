FROM dgcd/reference-snapshot:java-runtime-22

WORKDIR /kubelab/backend

COPY ./build/libs/kubelab-backend.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "kubelab-backend.jar"]
