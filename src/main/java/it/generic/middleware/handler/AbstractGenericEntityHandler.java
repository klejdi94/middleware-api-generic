package it.generic.middleware.handler;

import it.generic.middleware.service.ApiService;
import it.generic.middleware.service.GenericApiService;
import it.generic.middleware.model.GenericEntityI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public abstract class AbstractGenericEntityHandler implements GenericApi{

    /**
     *
     * @param <T>
     * @return
     */
    protected abstract @NonNull< T extends GenericApiService<GenericEntityI>> T getRepositoryService();

    /**
     *
     * @return
     */
    protected abstract @NonNull Class<? extends GenericEntityI>  getRequestEntityType();

    /**
     *
     * @return
     */
    protected abstract @NonNull Class<? extends GenericEntityI>  getResponseEntityType();

    /**
     *
     * @return
     */
    protected abstract @NonNull String getEntityId();

    /**
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        return getRepositoryService()
                .getAll()
                .flatMap(ApiService::okResponse);
    }


    /**
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> add(ServerRequest serverRequest) {
        return serverRequest
                .body(BodyExtractors.toMono(getRequestEntityType()))
                .flatMap(getRepositoryService()::addIfNotExist)
                .flatMap(ApiService::entityCreated);
    }

    /**
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        final String entityId = serverRequest.pathVariable(getEntityId());
        return getRepositoryService()
                .getIfExist(entityId)
                .flatMap(ApiService::okResponse);
    }

    /**
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> updateById(ServerRequest serverRequest) {
        return serverRequest
                .body(BodyExtractors.toMono(getRequestEntityType()))
                .flatMap(getRepositoryService()::updateIfExist)
                .flatMap(ApiService::okResponse);
    }

    /**
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
        final String entityId = serverRequest.pathVariable(getEntityId());
        return getRepositoryService()
                .deleteIfExist(entityId)
                .flatMap(ApiService::okResponse);
    }

    /**
     *
     * @param serverRequest
     * @return
     */
    @Override
    public Mono<ServerResponse> deleteAll(ServerRequest serverRequest) {
        return getRepositoryService()
                .deleteAll()
                .flatMap(ApiService::okResponse);
    }
}
