FROM openjdk:8-jdk-alpine
ARG IDLE_PROFILE
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
ENV ENV_IDLE_PROFILE=$IDLE_PROFILE
COPY ${JAR_FILE} /app.jar
RUN echo $ENV_IDLE_PROFILE
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=${ENV_IDLE_PROFILE}"]


