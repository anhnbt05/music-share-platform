# ----------------------------------------------------------------------------------------------------------------------
# Build Stage: Compile Spring Boot app với Gradle
# ----------------------------------------------------------------------------------------------------------------------
FROM gradle:8.7-jdk21-alpine AS build
WORKDIR /build

# Copy gradle files first (caching layer)
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/wrapper gradle/wrapper

# Download dependencies (caching)
RUN chmod +x ./gradlew && ./gradlew dependencies --no-daemon

# Copy source code và build JAR
COPY src ./src
RUN ./gradlew clean bootJar -x test --no-daemon

# ----------------------------------------------------------------------------------------------------------------------
# Runtime Stage: lightweight container
# ----------------------------------------------------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine
EXPOSE 8080

ARG PROFILE=prod
WORKDIR /app

# Copy built JAR từ build stage
COPY --from=build /build/build/libs/*.jar app.jar

# Application profile
ENV ACTIVE_PROFILE=${PROFILE}

CMD ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-jar", "app.jar"]