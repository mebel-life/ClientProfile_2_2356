FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileUpdate-1.0-SNAPSHOT.jar
WORKDIR /update
EXPOSE 8089
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]