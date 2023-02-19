FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileCommon-1.0-SNAPSHOT.jar
WORKDIR /common
EXPOSE 8083
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]