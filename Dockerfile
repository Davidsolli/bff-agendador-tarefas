FROM gradle:8.5-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/app.jar app.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/app.jar"]
