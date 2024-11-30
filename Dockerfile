# Use a lightweight JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the Maven build output
COPY target/healthsync-notifi-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]