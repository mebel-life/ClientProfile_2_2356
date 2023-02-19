FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileNotification-1.0-SNAPSHOT.jar
WORKDIR /notification
EXPOSE 8086
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]