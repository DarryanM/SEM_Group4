FROM openjdk:latest
COPY ./target/SEM_Group4.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEM_Group4.jar"]