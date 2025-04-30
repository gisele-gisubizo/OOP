# Use OpenJDK 21 slim as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java classes from IntelliJ build
COPY out/production/MissionManagementSystem/ /app/

# Command to run the main class
CMD ["java", "com.gisele.missionmanagement.Main"]