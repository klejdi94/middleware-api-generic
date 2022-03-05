FROM openjdk:8-jdk-alpine

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

COPY src src

RUN ./mvnw  package  


ARG JAR_FILE=target/*.jar
RUN mv ${JAR_FILE} middleware-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/middleware-0.0.1-SNAPSHOT.jar"]
