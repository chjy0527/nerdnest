FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copy gradle wrapper files first
COPY gradle ./gradle
COPY gradlew ./

# Make gradlew executable
RUN chmod +x ./gradlew

RUN ./gradlew wrapper

# Copy gradle configuration files
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy the source code
COPY src ./src

# Build the application using Gradle build cache
RUN ./gradlew build --build-cache --no-daemon -x test

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set environment variables for production
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the port the app runs on
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]