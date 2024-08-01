FROM openjdk:17-jdk-alpine

ARG JAR_FILE=build/libs/*.jar

WORKDIR /benchmark

COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-jar", "-Djava.net.preferIPv4Stack=true", "/app.jar"]