FROM amazoncorretto:21-alpine

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENV PORT=10000

ENTRYPOINT ["java", "-jar", "/app.jar", "--server.address=0.0.0.0"]