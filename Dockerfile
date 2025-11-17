FROM eclipse-temurin:21

# Megabox Spring Boot Application JAR
COPY build/libs/*SNAPSHOT.jar megabox-app.jar

# Run Megabox Application
ENTRYPOINT ["java", "-jar", "/megabox-app.jar"]