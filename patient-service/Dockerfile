#comes with jdk
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

#caches the container content and if no change was made, uses the same load, making the load faster
RUN mvn dependency:go-offline -B

COPY src ./src

#creates a .jar file with all the stuff
RUN mvn clean package


FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]