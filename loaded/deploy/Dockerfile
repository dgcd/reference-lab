FROM dgcd/reference-snapshot:java-runtime-21

WORKDIR /home/starter

COPY ../build/libs/reference-loaded.jar .

USER starter:starter

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "reference-loaded.jar"]
