FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE=target/ClientProfileLoader-1.0-SNAPSHOT.jar
WORKDIR /loader
EXPOSE 8085
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]