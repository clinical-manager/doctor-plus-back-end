FROM openjdk:17-alpine

COPY . /usr/app
WORKDIR /usr/app
RUN chmod +x mvnw \
    && ./mvnw --version \
    && ./mvnw clean package -DskipTests

ENV APP_NAME doctor-plus

COPY --from=build /usr/app/target/*.jar /app/${APP_NAME}.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=hlg", "-jar","/app/${APP_NAME}.jar", "-XX:MaxRAMPercentage=85.0 ","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap"]
