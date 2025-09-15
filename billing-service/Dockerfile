#using maven's base image as builder
FROM maven:3.9.9-eclipse-temurin-21 as builder

WORKDIR /app

COPY pom.xml .

#install all dependencies using pom.xml, and caches dependencies to build faster if there is no change
RUN mvn dependency:go-offline -B

COPY src ./src

#Runs Maven build inside the container:
#clean → deletes old build outputs
#package → compiles code, runs tests, creates a JAR (e.g., target/billing-service-0.0.1-SNAPSHOT.jar).
RUN mvn clean package

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]