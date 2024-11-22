FROM dgcd/reference-snapshot:java-runtime-23

WORKDIR /home/starter

COPY app/build/libs/reference-app.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "reference-app.jar"]
