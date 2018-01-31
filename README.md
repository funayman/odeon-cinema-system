# Odeon Cinema System
This is the final project for my java course while taking my masters.

## Assignment Requirements
The Odeon Cinema needs software for managing bookings made by customers, the screens, the films and the timing of each projection.

The cinema has six screens and all contain 50 seats, 10 of which are VIP seats (extra room for legs, bigger chair).

For each screen, there are 4 shows per day: 1 in the afternoon, 1 in the evening and 2 at night.

A customer who wants to book a ticket, needs to select the specific show time. The pricing of the shows is different: the afternoon show is the cheapest, the first night show is the most expensive.

Customers can decide to pay using a credit card or cash, and may change the date/time of the show, provided there are still seats available for the new selected date.
After the show, customers are able to write a review of the film they watched and provide a numerical rating of the film ranging from 1 to 5. All this information will be recorded in the system.

After each month, the user of the system must print:
* a report containing the number of spectators per film, along with the average rating;
* a report containing the film which generated the highest income.

In your final deliverable, provide data covering at least 5 films, 15 customers, 15 seats (5 of which VIP seats) and 10 reviews (with rating), which can then be displayed in the output reports.

## Run the application
```bash
$ ./mvnw clean package -Dmaven.test.skip=true && java -jar target/odeon-cinema-system-1.0.0-beta.jar
```
Using the Spring Boot plugin
```bash
$ ./mvnw clean spring-boot:run
```
After everything has loaded, go to [localhost:8080](http://localhost:8080) to use the system.

## Building
### Using Maven
By default, the project comes with a maven wrapper that will download the correct maven version required to build the project locally.
```bash
$ ./mvnw clean package -Dmaven.test.skip=true
```

### Using Docker
```bash
$ docker run --rm -v "$(pwd)":/app -w /app maven:3.5.0-jdk-8-alpine mvn clean package
```

By default, the `.m2` folder in the docker images is cleared after shutdown.
If you would like to save the downloaded JARs for faster deployment, you can save it to a docker volume and then mount it to `/root/.m2`
```bash
$ docker volume create --name maven-repo
```
And then build using
```bash
$ docker run --rm -v maven-repo:/root/.m2 -v "$(pwd)":/app -w /app maven:3.5.0-jdk-8-alpine mvn clean package
```

### Download a Precompiled JAR
If you don't have Docker or Maven or the JDK installed on your machine you can download an [Uber-JAR](https://stackoverflow.com/a/11947093) of the project under the [Releases Page](https://github.com/funayman/odeon-cinema-system/releases/tag/v1.0-beta)

## Deployment
By default, the JAR file will be built and located in `{PROJECT_DIR}/target`

### Using Java
```bash
$ java -jar target/odeon-cinema-system-1.0.0-beta.jar
```

### Using Docker
If you don't have Java installed on your machine and want to use docker after a build:
```bash
$ docker run --rm -v "$(pwd)":/app -w /app -p 8080:8080 maven:3.5.0-jdk-8-alpine java -jar target/odeon-cinema-system-1.0.0-beta.jar
```
