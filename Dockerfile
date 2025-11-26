FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN apk add --no-cache bash

RUN chmod +x ./gradlew

RUN ./gradlew clean build -x test --info

RUN cp build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "-Dserver.port=8080", "app.jar"]