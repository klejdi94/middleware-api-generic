package it.generic.middleware.handler.test;

import it.generic.middleware.handler.AbstractGenericEntityHandler;
import it.generic.middleware.model.test.Test;
import it.generic.middleware.service.test.TestService;
import it.generic.middleware.config.routers.test.TestConfig;
import it.generic.middleware.model.GenericEntityI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

}
