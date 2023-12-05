FROM dgcd/reference-snapshot:java-runtime-21

WORKDIR /home/starter

COPY ../build/libs/reference-config.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "reference-config.jar"]
