FROM openjdk:8
ADD /application/target/application-0.0.1.jar application.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/application.jar"]
ds
