# Weather Forecast
Provides an API to retrieve the weather forecast and, given a city, provides the following information for the next 3 days:
1. average maximum/minimum temperatures and humidity during the working hours;
2. average maximum/minimum temperatures and humidity outside the working hours.

## Prerequisites for building
1. [Maven](https://maven.apache.org/) - Dependency Management
2. JDK 1.8

## Prerequisites for running in container environment
1. [Docker](https://www.docker.com/get-started)
2. [Docker Compose](https://www.docker.com/get-started)

## Quick start
To use it from the source code:
```bash
$ mvn clean package
$ cd target/
$ java -Dspring.profiles.active=demo -Xms1024m -Xmx1024m -jar weather-forecast.jar
```

To use it from the Docker Image:
```bash
$ ./build-with-docker.sh
$ docker-compose up
```

## Documentation
To see the documentation and try the api , [Swagger](https://swagger.io/) is used.
Visit [this](http://localhost:5000/api/swagger-ui.html) page to open the documentation for the Forecast API and to test it.