FROM openjdk:8
ADD build/libs/notification-0.0.1-SNAPSHOT.jar notification-0.0.1-SNAPSHOT.jar
EXPOSE 10000
WORKDIR /
ENTRYPOINT ["java","-jar","notification-0.0.1-SNAPSHOT.jar"]