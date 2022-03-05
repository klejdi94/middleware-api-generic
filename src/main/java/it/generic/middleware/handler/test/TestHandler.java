package it.generic.middleware.handler.test;

import it.generic.middleware.handler.AbstractGenericEntityHandler;
import it.generic.middleware.model.test.Test;
import it.generic.middleware.service.test.TestService;
import it.generic.middleware.config.routers.test.TestConfig;
import it.generic.middleware.model.GenericEntityI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
public class TestHandler extends AbstractGenericEntityHandler {

    /**
     *
     */
    @Autowired
    TestService testService;

    /**
     *
     * @return
     */
    @Override
    protected TestService getRepositoryService() { return testService;}

    /**
     *
     * @return
     */
    @Override
    protected Class<? extends GenericEntityI> getRequestEntityType() {
        return Test.class;
    }

    /**
     *
     * @return
     */
    @Override
    protected Class<? extends GenericEntityI> getResponseEntityType() {
        return Test.class;
    }

    /**
     *
     * @return
     */
    @Override
    protected String getEntityId() {
        return TestConfig.Paths.Parameters.ID;
    }

    public Mono<ServerResponse> getHello(ServerRequest serverRequest) {
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Welcome, start using this middleware demo project, start at /api/v1/test , Enjoy!");
    }
}
