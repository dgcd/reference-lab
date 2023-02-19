FROM eclipse-temurin:19-jre-focal

LABEL maintainer="dgcd"

RUN apt update && \
    apt upgrade -y && \
    apt autoremove && \
    apt clean && \
    addgroup --gid 4242 starter && \
    adduser --gecos "" --quiet --disabled-password --home /home/starter --uid 4242 --gid 4242 starter

WORKDIR /home/starter

COPY ../build/libs/reference-config.jar .

USER starter:starter

ENTRYPOINT ["java", "-jar", "reference-config.jar"]
