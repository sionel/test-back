FROM openjdk:8-jdk-slim
VOLUME /tmp
ADD target/back-0.0.1-SNAPSHOT.jar back.jar
ENTRYPOINT ["java","-jar", "/back.jar"]