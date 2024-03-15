FROM maven:3.6.3-openjdk-17
VOLUME /tmp
EXPOSE 8080
COPY target/pecs-0.0.1-SNAPSHOT.jar pecs.jar
ENTRYPOINT ["java","-jar","/pecs.jar"]