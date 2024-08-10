# Library api

## Docs

> Postman collection is available in docs directory


## Prerequisites

1- docker<br/>
2- docker-compose


### Test

> Coverage reports will appear after run unit tests in this path (target -> site -> jacoco).<br/>

1- Run unit tests
```console
mvn clean test
```


### Setup

1- copy .env file
```console
cp .env.example .env
```

2- build images and run containers
```console
docker-compose -f ./docker/docker-compose.yml -f ./docker/docker-compose-prod.yml --env-file ./.env up
```
