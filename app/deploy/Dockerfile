FROM eclipse-temurin:19-jre-focal

LABEL maintainer="dgcd"

RUN addgroup --gid 4242 starter && \
    adduser --gecos "" --quiet --disabled-password --home /home/starter --uid 4242 --gid 4242 starter

WORKDIR /home/starter

COPY app/build/libs/reference-app.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "reference-app.jar"]
