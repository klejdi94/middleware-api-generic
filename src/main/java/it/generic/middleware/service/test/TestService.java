package it.generic.middleware.service.test;

import it.generic.middleware.model.test.Test;
import it.generic.middleware.repository.TestRepository;
import it.generic.middleware.service.AbstractApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService extends AbstractApiService {

    /**
     *
     */
    @Autowired
    private TestRepository testRepository;

    /**
     *
     * @return
     */
    @Override
    protected TestRepository getRepository() {return testRepository;}

    /**
     *
     * @return
     */
    @Override
    protected  Test getGenericEntity() { return new Test(); }
}
