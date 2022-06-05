# Weather API Service
Weather API is a Restful service written by Java.

## Features

- Add weather information for your own city
- Update weather information for your own city
- Search for praticular range of weather information for requested city
- Delete weather information for requested city


## Tech-Stack

Weather API uses a number of open source projects to work properly:

- [Spring Boot](https://spring.io/projects/spring-boot) - Java backend framework
- [Hibernate](https://hibernate.org/) - Hibernate ORM enables developers to more easily write applications whose data outlives the application process.
- [JPA](https://spring.io/projects/spring-data-jpa) - Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories


## Installation

Import this project as a gradle project with "Open-Weather-Service\weather"


Install and build the dependencies and devDependencies.
```sh
./gradlew build
```

run service

```sh
./gradlew bootRun
```

## Environment Setup
Import the .sql file within /db folder.
Configurate your db password/account and Open Weather API key within application.properties file.


## API Endpoints

|Endpoint          | What it does                                 | Request type |
|------------------|----------------------------------------------|--------------|
|/weather?cityName={cityName}    |return weather data for particular city | GET|
|/weather/search?fromDateTime={fromDateTime}&toDateTime={toDateTime}| return particular range of weather data     | GET|
|/weather/search/city?cityName={cityName}|return current weather data for particular city| GET
|/weather        |add weather data for particular city| POST|
|/weather/{cityId}|update weather data for particular city|PUT|
|/weather/{cityId}|delete weather data for particular city|DELETE|