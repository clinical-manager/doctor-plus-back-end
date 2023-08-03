FROM openjdk:17-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENV JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=hlg", "-jar","/app.jar", "-XX:MaxRAMPercentage=85.0 ","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap"]
