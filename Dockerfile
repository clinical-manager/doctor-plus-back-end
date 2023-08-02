FROM openjdk:17-alpine

ENV APP_NAME doctor-plus

COPY ./target/*.jar /app/${APP_NAME}.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=hlg", "-jar","/app/${APP_NAME}.jar", "-XX:MaxRAMPercentage=85.0 ","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap"]
