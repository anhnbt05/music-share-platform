FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN apk add --no-cache bash

RUN chmod +x ./gradlew && ./gradlew clean build -x test

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "build/libs/musicshareserver-0.0.1-SNAPSHOT.jar"]