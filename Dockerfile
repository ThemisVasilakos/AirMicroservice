FROM openjdk:17
EXPOSE 8080:8080
ADD target/air-microservice.jar air-microservice.jar
ENTRYPOINT ["java","-jar","/air-microservice.jar"]