FROM openjdk:17-alpine3.13
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","/application.jar"]
#FROM adoptopenjdk:11-jre-hotspot
#ADD target/spring-boot-docker.jar spring-boot-docker.jar
## COPY out/artifacts/demo_jar/demo.jar spring-boot-docker.jar
#ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
