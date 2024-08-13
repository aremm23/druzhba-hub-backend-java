FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml /app
COPY robotic-door-426907-q2-0b53edbba1e7.json /app
COPY src /app/src
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar application.jar
COPY --from=build /app/robotic-door-426907-q2-0b53edbba1e7.json /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]
