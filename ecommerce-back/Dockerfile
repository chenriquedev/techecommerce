FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build
COPY . /build/
RUN mvn clean install -DskipTests

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT [ "java" ]
CMD ["-jar", "app.jar"]