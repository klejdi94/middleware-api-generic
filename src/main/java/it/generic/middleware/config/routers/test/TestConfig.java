package it.generic.middleware.config.routers.test;

import it.generic.middleware.handler.test.TestHandler;
import it.generic.middleware.config.ApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@Slf4j
public class TestConfig {

    /**
     *
     */
    private final ApiConfig apiConfig;

    /**
     *
     */
    private final TestHandler testHandler;

    /**
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> createTestRouterFunction() {
        return route().path(apiConfig.getApiPath(),
                builder -> builder.nest(accept(MediaType.APPLICATION_JSON), builder2 -> builder2
                        .path(Paths.ROOT,
                                builderDomains -> builderDomains
                                        .GET("", testHandler::get)
                                        .POST("", testHandler::add)
                                        .PATCH(Paths.BY_ID, testHandler::updateById)
                                        .GET(Paths.BY_ID, testHandler::getById)
                                        .DELETE("", testHandler::deleteAll)
                                        .DELETE(Paths.BY_ID, testHandler::deleteById)
                        ).path("",
                                builderDomains -> builderDomains
                                        .GET("", testHandler::getHello)
                        )
                )).build();
    }

    @Bean
    public RouterFunction<ServerResponse> createWelcomeRouterFunction() {
        return route().path("",
                builder -> builder.nest(accept(MediaType.APPLICATION_JSON), builder2 -> builder2
                        .path("",
                                builderDomains -> builderDomains
                                        .GET("", testHandler::getHello)
                        )
                )).build();
    }


    @UtilityClass
    public static class Paths {
        /**
         *
         */
        public static final String ROOT = "/test";

        /**
         *
         */
        public static final String BY_ID = "/{" + Parameters.ID + "}";

        /**
         *
         */
        @UtilityClass
        public static class Parameters {

            /**
             *
             */
            public static final String ID = "testId";
        }

    }
}
