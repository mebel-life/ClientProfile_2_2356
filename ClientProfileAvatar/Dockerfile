FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileAvatar-1.0-SNAPSHOT.jar
WORKDIR /avatar
EXPOSE 8082
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]