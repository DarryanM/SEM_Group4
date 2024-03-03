FROM openjdk:latest
COPY ./target/SEM-Group4-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEM-Group4-0.1.0.1-jar-with-dependencies.jar"]