FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=hlg", "-jar","/app.jar", "-XX:MaxRAMPercentage=85.0 ","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap"]
