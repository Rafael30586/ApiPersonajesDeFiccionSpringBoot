FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/api_personajes_de_ficcion-1.0.0.jar
COPY ${JAR_FILE} app_personajes.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_personajes.jar"]