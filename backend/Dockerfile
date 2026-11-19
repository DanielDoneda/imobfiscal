FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /app/target/imobfiscal-backend-1.0.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Xms64m -Xmx384m -XX:+UseSerialGC -jar app.jar"]
