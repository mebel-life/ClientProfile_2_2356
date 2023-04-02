FROM openjdk:17-jdk-alpine as app
#RUN apt-get update && apt-get install -y --no-install-recommends openjfx && rm -rf /var/lib/apt/lists/*
COPY target/ClientProfileNotification-1.0-SNAPSHOT.jar ClientProfileNotification.jar
ENTRYPOINT ["java", "-jar", "ClientProfileNotification.jar"]
FROM openjdk:17-jdk-alpine
