FROM openjdk:latest
COPY ./target/SEM_Group4-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEM_Group4-0.1.0.2-jar-with-dependencies.jar"]