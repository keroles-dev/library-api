# Library api

## Prerequisites

1- docker<br/>
2- docker-compose


### Test

> You can find postman collection in docs directory

### Setup

1- copy .env file
```console
cp .env.example .env
```

3- build images and run containers
```console
docker-compose -f ./docker/docker-compose.yml -f ./docker/docker-compose-prod.yml --env-file ./.env up
```
