FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileService-1.0-SNAPSHOT.jar
WORKDIR /service
EXPOSE 8088
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]