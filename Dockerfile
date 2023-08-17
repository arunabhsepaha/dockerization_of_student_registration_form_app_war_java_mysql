# Use the official Maven image to build the application
FROM maven:3.8.4-jdk-11 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn clean install -DskipTests

# Copy the rest of the application code
COPY src/ ./src/
RUN mvn package -DskipTests

# Use a lightweight Java runtime image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR or WAR file from the builder stage
COPY --from=builder /app/target/Web_Mysql_App-0.0.1-SNAPSHOT.war app.war

# Expose the application port
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "app.war"]

