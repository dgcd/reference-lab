FROM eclipse-temurin:24-jdk as jre-build

RUN java --list-modules && \
    $JAVA_HOME/bin/jlink \
         --add-modules java.base,java.logging,java.sql,java.naming,java.management,java.instrument,java.desktop,java.se,java.security.jgss,jdk.unsupported,jdk.crypto.ec,jdk.crypto.cryptoki \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime


FROM debian:bookworm-slim

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"

COPY --from=jre-build /javaruntime $JAVA_HOME

#RUN apt update && \
#    apt upgrade -y && \
#    apt autoremove && \
#    apt clean && \
RUN addgroup --gid 4242 starter && \
    adduser --gecos "" --quiet --disabled-password --home /home/starter --uid 4242 --gid 4242 starter

LABEL maintainer="dgcd"
