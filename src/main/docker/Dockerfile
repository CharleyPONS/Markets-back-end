FROM adoptopenjdk:16-jre-hotspot
LABEL maintainer="Charley PONS"

RUN cd ../../../ && ./mvnw clean package -DskipTests && cp target/docker-spring-boot-postgres-0.0.1-SNAPSHOT.jar src/main/docker
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
