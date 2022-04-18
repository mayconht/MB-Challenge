FROM openjdk:8-alpine AS RUNNER

COPY . .
EXPOSE 8080


CMD ["java", "-jar", "/out/artifacts/MuchBetter_Challenge_jar/MuchBetter-Challenge.jar"]