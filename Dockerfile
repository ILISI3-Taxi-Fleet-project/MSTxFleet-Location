FROM openjdk:17-jdk-alpine
COPY target/MSTxFleet-Location-0.0.1-SNAPSHOT.jar MSTxFleet-Location.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/MSTxFleet-Location.jar"]