package it.generic.middleware.repository;

import it.generic.middleware.model.test.Test;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ReactiveCrudRepository<Test,String> {
}
