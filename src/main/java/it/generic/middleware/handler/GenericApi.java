package it.generic.middleware.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface GenericApi {

    /**
     *
     * @param serverRequest
     * @return
     */
    Mono<ServerResponse> get(@NonNull final ServerRequest serverRequest);

    /**
     *
     * @param serverRequest
     * @return
     */
    Mono<ServerResponse> add(@NonNull final ServerRequest serverRequest);


    /**
     *
     * @param serverRequest
     * @return
     */
    Mono<ServerResponse> getById(@NonNull final ServerRequest serverRequest);


    /**
     *
     * @param serverRequest
     * @return
     */
    Mono<ServerResponse> updateById(@NonNull final ServerRequest serverRequest);


    /**
     *
     * @param serverRequest
     * @return
     */
    Mono<ServerResponse> deleteById(@NonNull final ServerRequest serverRequest);


    /**
     *
     * @param serverRequest
     * @return
     */
    Mono<ServerResponse> deleteAll(@NonNull final ServerRequest serverRequest);



}
