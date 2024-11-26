FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/frontend-1.0-SNAPSHOT.jar /app/frontend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "frontend.jar"]
