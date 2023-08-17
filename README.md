# Dockerization of Student Registration Form App (Java + MySQL)

This repository contains a Dockerized Java Spring Boot application for a Student Registration Form, integrated with a MySQL database. This README provides an overview of how the project is Dockerized, the optimizations and best practices used, and a summary of the application's features.

## Table of Contents

- [Dockerization](#dockerization)
  - [Dockerfile](#dockerfile)
  - [docker-compose.yml](#docker-composeyml)
- [Optimizations and Best Practices](#optimizations-and-best-practices)
- [Application Summary](#application-summary)
  - [UI Output](#ui-output)
- [Getting Started](#getting-started)
- [Dependencies](#dependencies)

## Dockerization

The project is Dockerized using a multi-stage Docker build process to create a lightweight and efficient Docker image.

### Dockerfile

The `Dockerfile` is structured as follows:
```Dockerfile
# Use the official Maven image to build the application
FROM maven:3.8.4-jdk-11 AS builder

# ... (Build Stage: Dependencies Installation and Application Compilation) ...

# Use a lightweight Java runtime image for the final image
FROM openjdk:11-jre-slim

# ... (Final Stage: Copying Application Artifacts and Starting Application) ...
```
## docker-compose.yml
The docker-compose.yml file defines a multi-container environment, including the application container and a MySQL database container. It also specifies networking and volume configurations.
```yaml
version: "3"
services:
  application:
    # ... (Application Service Configuration) ...
  
  mysqldb:
    # ... (MySQL Database Service Configuration) ...

networks:
  springboot-db-net:

```


## Optimizations and Best Practices

#### Multi-Stage Builds
The Dockerfile utilizes multi-stage builds to separate the build and runtime stages, optimizing the final image size.

#### Dependency Management
Maven is used to manage project dependencies, ensuring a consistent and reproducible build process.
##### Tests Skipped During Build:
  Tests are skipped during the Maven build process using the `-DskipTests` flag in the `Dockerfile`.
Skipping tests helps avoid potential issues with the database not being ready during build, which could lead to test failures.

#### depends_on Attribute (docker-compose.yml):
The `depends_on` attribute in `docker-compose.yml` specifies the order in which services are started.
In this project, the "application" service depends on the "mysqldb" service, ensuring that the database is up and running before the application starts.

#### Container Networking
The application and MySQL database are connected using a shared network, `springboot-db-net`, enabling secure communication.

#### Volume Mounts
Volume mounts are used to persist data for the MySQL database and the Spring Boot application, allowing data to be preserved between container restarts.

## Application Summary
The Student Registration Form App is a Java Spring Boot application integrated with a MySQL database. It provides a user interface for registering students and viewing student information.

## UI Output
When the application is running, accessing the UI in a web browser will present the following pages:

#### Student Registration Form (1st Page):
- Name
- Email
- Gender (Male/Female)
- Course
- Timings (Morning/Afternoon/Evening)
- Link to the 2nd page: View Students

#### View Students (2nd Page):
- Table displaying student information:
  - S.No
  - Name
  - Email
  - Gender
  - Course
  - Timings

## Getting Started
To run this application, make sure you have Docker and Docker Compose installed on your system.

1. Clone this repository:
   ```bash
   git clone https://github.com/arunabhsepaha/dockerization_of_student_registration_form_app_war_java_mysql.git
   ```
2. Navigate to the project folder:
   ```bash
   cd dockerization_of_student_registration_form_app_war_java_mysql
   ```
4. Run the application using Docker Compose:
   ```bash
   docker-compose up -d
   ```
5. Access the application UI by opening a web browser and navigating to
   `http://localhost:8080`.

## Cleanup
To stop and remove the Docker containers, network, and volumes, run the following command:
```bash
docker-compose down
   ```

## Dependencies
The project uses the following major dependencies:

- Java 11
- Spring Boot 2.7.14
- MySQL 5.7
- Maven

For a comprehensive list of dependencies, please refer to the pom.xml file in the project.


