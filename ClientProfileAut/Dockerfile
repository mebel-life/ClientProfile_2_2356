FROM openjdk:17-jdk-alpine as app
COPY target/ClientProfileAut-2.6.2.jar ClientProfileAut.jar
ENTRYPOINT ["java", "-jar", "ClientProfileAut.jar"]