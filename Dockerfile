FROM openjdk:8-jdk-alpine
FROM maven:3.6-jdk-11 as maven_build
COPY pom.xml .
COPY src src

RUN mvn  package


ARG JAR_FILE=target/*.jar
RUN mv ${JAR_FILE} middleware-0.0.1-SNAPSHOT.jar
EXPOSE 90
EXPOSE 9090:9090
EXPOSE 9091:9090
ENV MONGO_DB_URL=${MONGO_DB_URL}
CMD java -jar middleware-0.0.1-SNAPSHOT.jar -Djdk.tls.client.protocols=TLSv1.2
