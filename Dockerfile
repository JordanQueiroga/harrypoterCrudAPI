FROM openjdk:8-jdk-alpine as build

VOLUME /tmp

COPY target/api-harry-potter-*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

#docker-compose -f docker-compose.yml up --build
