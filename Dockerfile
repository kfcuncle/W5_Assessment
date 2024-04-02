# First stage: build the application
FROM openjdk:17

WORKDIR /app

COPY ../target/W5-Assessment-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "W5-Assessment-0.0.1-SNAPSHOT.jar"]