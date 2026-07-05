# Lightweight Java 21 runtime
FROM eclipse-temurin:21-jre-alpine

# Create application directory
WORKDIR /app

# Copy the Spring Boot JAR
COPY target/*.jar app.jar

# Application listens on port 8080
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]