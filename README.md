#  API Generic Middleware

[![CI](https://github.com/klejdi94/middleware-api-generic/actions/workflows/main.yml/badge.svg)](https://github.com/klejdi94/middleware-api-generic/actions/workflows/main.yml)
[![CodeQL](https://github.com/klejdi94/middleware-api-generic/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/klejdi94/middleware-api-generic/actions/workflows/codeql-analysis.yml)

* Java 11
* Spring WebFlux
* Reactive programming
* Reactive JPA Repository


1) Run mongo compose on dir docker-image
2) Start the project

### Create fast entities : 

model> YourEntity

repository> YourEntityRepository

service> YourEntityService

handler> YourHandler

config.routes> YourEntityConfig


### Api exposed : 

GET/POST/PUT/DELETE/PATCH : localhost:9090/api/v1/{entity}/{entityId}
actuator: localhost:9091/actuator 


PS.
Insert into your env: 
DOCKER_HUB_USERNAME
DOCKER_HUB_ACCESS_TOKEN 
MONGO_DB_URL 




