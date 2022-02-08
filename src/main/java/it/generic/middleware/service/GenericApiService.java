package it.generic.middleware.service;

import it.generic.middleware.model.GenericEntityI;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GenericApiService<E extends GenericEntityI> {

    /**
     *
     * @param list
     * @return
     */
    Flux<GenericEntityI> saveAll(@NonNull List<GenericEntityI> list);

    /**
     *
     * @return
     */
    Mono<List<GenericEntityI>> getAll();

    /**
     *
     * @return
     */
    Mono<Void> deleteAll();

    /**
     *
     * @param id
     * @return
     */
    Mono<Void> deleteIfExist(@NonNull final String id);

    /**
     *
     * @param id
     * @return
     */
    Mono<E> getIfExist(@NonNull final String id);

    /**
     *
     * @param entity
     * @return
     */
    Mono<E> addIfNotExist(@NonNull final E entity);

    /**
     *
     * @param entity
     * @return
     */
    Mono<E> updateIfExist(@NonNull final E entity);
}
