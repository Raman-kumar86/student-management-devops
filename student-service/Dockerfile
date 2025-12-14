# Use stable Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/student-service-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
