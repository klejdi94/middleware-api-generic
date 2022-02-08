#  API Generic Middleware

* Java 11
* Spring WebFlux
* Reactive programming
* Reactive JPA Repository


1) Run mongo compose on dir docker-image
2) Start the project

###Create fast entities : 

model> YourEntity

repository> YourEntityRepository

service> YourEntityService

handler> YourHandler

config.routes> YourEntityConfig


###Api exposed : 

GET/POST/PUT/DELETE/PATCH : localhost:9090/api/v1/{entity}/{entityId}

actuator: localhost:9091/actuator 

