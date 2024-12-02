FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/frontend-2.0.jar /app/frontend.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "frontend.jar"]
