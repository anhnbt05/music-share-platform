FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-alpine
EXPOSE 8080

ARG PROFILE=prod
WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

ENV ACTIVE_PROFILE=${PROFILE}

CMD ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-jar", "app.jar"]