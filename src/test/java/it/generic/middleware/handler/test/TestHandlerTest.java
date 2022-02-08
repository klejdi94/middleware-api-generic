package it.generic.middleware.handler.test;

import it.generic.middleware.repository.TestRepository;
import it.generic.middleware.service.test.TestService;
import it.generic.middleware.config.ApiConfig;
import it.generic.middleware.config.routers.test.TestConfig;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@WebFluxTest
@ContextConfiguration(
        classes = {TestConfig.class, TestHandler.class,
                ApiConfig.class, TestService.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestHandlerTest {

    @MockBean
    TestRepository testRepository;

    @MockBean
    TestService testService;

    @Autowired
    private ApplicationContext context;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .build();
    }

    @Test
    void test_1_createTest() {
        it.generic.middleware.model.test.Test test =
                it.generic.middleware.model.test.Test.builder()
                        .cognome("PROVA")
                        .nome("PROVA")
                        .build();
        test.setId("IDTEST");

        Mockito.when(testRepository.existsById(test.getId())).thenReturn(Mono.just(false));
        Mockito.when(testService.addIfNotExist(test)).thenReturn(Mono.just(test));

        webTestClient
                .post()
                .uri("/api/v1/test")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(test))
                .exchange()
                .expectStatus().isCreated();
    }


    @Test
    void test_2_getTest() {
        it.generic.middleware.model.test.Test test =
                it.generic.middleware.model.test.Test.builder()
                        .cognome("PROVA")
                        .nome("PROVA")
                        .build();
        test.setId("IDTEST");

        Mockito.when(testService.getIfExist(test.getId())).thenReturn(Mono.just(test));

        webTestClient.get().uri("/api/v1/test/{id}", test.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nome").isNotEmpty()
                .jsonPath("$._id").isEqualTo(test.getId())
                .jsonPath("$.cognome").isEqualTo("PROVA");
    }

    @Test
    void test_3_updateTest() {
        it.generic.middleware.model.test.Test test =
                it.generic.middleware.model.test.Test.builder()
                        .cognome("PROVA")
                        .nome("PROVA")
                        .build();
        test.setId("IDTEST");

        it.generic.middleware.model.test.Test test1 =
                it.generic.middleware.model.test.Test.builder()
                        .cognome("PROVAMODIFICATO")
                        .nome("PROVAMODIFICATO")
                        .build();
        test1.setId("IDTEST");

        Mockito.when(testRepository.findById(test.getId())).thenReturn(Mono.just(test));
        Mockito.when(testService.updateIfExist(test)).thenReturn(Mono.just(test1));


        webTestClient
                .patch()
                .uri("/api/v1/test/{id}", test.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(test))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.cognome").isEqualTo("PROVAMODIFICATO");
    }


    @Test
    void test_4_deleteTest() {
        it.generic.middleware.model.test.Test test =
                it.generic.middleware.model.test.Test.builder()
                        .cognome("PROVA")
                        .nome("PROVA")
                        .build();
        test.setId("IDTEST");

        Mockito.when(testRepository.findById(test.getId())).thenReturn(Mono.just(test));
        Mockito.when(testService.deleteIfExist(test.getId())).thenReturn(Mono.empty());

        webTestClient
                .delete()
                .uri("/api/v1/test/{id}", test.getId())
                .exchange()
                .expectStatus().isOk();
    }

}
