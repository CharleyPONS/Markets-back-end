FROM adoptopenjdk:16-jre-hotspot
LABEL maintainer="Charley PONS"

COPY  ./target/Markets-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
