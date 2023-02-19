FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileRecognition-1.0-SNAPSHOT.jar
WORKDIR /recognition
EXPOSE 8087
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]