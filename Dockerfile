FROM maven:3.6.3-jdk-8 AS MAVEN_BUILD
MAINTAINER Bledi Nukaj
COPY pom.xml /build/
COPY src /build/src

WORKDIR /build/
RUN mvn clean package -DskipTests

FROM openjdk:8
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/weather-forecast.jar /app/
EXPOSE 5000
ENTRYPOINT ["java","-jar","weather-forecast.jar"]