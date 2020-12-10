FROM openjdk:8-jdk-alpine
MAINTAINER jinubb
ARG JAR_FILE=/target/wc-login-service-0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 4446

