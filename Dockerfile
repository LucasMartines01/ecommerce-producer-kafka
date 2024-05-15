FROM maven:3-amazoncorretto-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT java -jar app.jar