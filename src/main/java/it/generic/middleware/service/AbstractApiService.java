package it.generic.middleware.service;

import it.generic.middleware.model.GenericEntity;
import it.generic.middleware.model.GenericEntityI;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public abstract class AbstractApiService implements GenericApiService<GenericEntityI> {

    /**
     * @param <T>
     * @return
     */
    protected abstract <T extends ReactiveCrudRepository<GenericEntityI, String>> T getRepository();

    /**
     * @param <T>
     * @return
     */
    protected abstract <T extends GenericEntity> T getGenericEntity();


    /**
     * @return
     */
    @Override
    public Mono<List<GenericEntityI>> getAll() {
        return getRepository().findAll().collectList();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<GenericEntityI> getIfExist(@NonNull final String id) {
        return getRepository()
                .findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Mono<GenericEntityI> addIfNotExist(@NonNull GenericEntityI entity) {
        return getRepository()
                    .existsById(settingId(entity).getId())
                    .flatMap(exist -> {
                        if (exist) {
                            return ApiService.entityConflict(entity);
                        }
                        return Mono.just(entity);
                    })
                    .flatMap(ent -> this.convert(entity, false))
                    .flatMap(getRepository()::save);
    }

    private GenericEntityI settingId(GenericEntityI entity) {
        ObjectId id = new ObjectId();
        entity.setId(id.toString());
        return entity;
    }


    /**
     * @param entity
     * @return
     */
    @Override
    public Mono<GenericEntityI> updateIfExist(GenericEntityI entity) {
        return getRepository()
                .findById(entity.getId())
                .flatMap(exist -> {
                    if (Objects.nonNull(exist)) {
                        entity.setCreatedAt(exist.getCreatedAt());
                        return Mono.just(entity);
                    }
                    return ApiService.entityNotFound(entity);
                })
                .flatMap(ent -> this.convert(entity, true))
                .flatMap(getRepository()::save);
    }

    /**
     * @param list
     * @return
     */
    @Override
    public Flux<GenericEntityI> saveAll(List<GenericEntityI> list) {
        return getRepository().saveAll(list);
    }

    /**
     * @return
     */
    @Override
    public Mono<Void> deleteAll() {
        return getRepository().deleteAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Void> deleteIfExist(String id) {
        return getIfExist(id)
                .flatMap(genericEntity -> getRepository().deleteById(id));
    }

    /**
     * @param toConvert
     * @param update
     * @return
     */
    protected Mono<GenericEntityI> convert(GenericEntityI toConvert, boolean update) {
        final GenericEntity genericEntity = getGenericEntity();
        if (Objects.isNull(genericEntity)) {
            setTimestamp(toConvert, update);
            return Mono.just(toConvert);
        }
        BeanUtils.copyProperties(toConvert, genericEntity);
        setTimestamp(genericEntity, update);
        return Mono.just(Objects.requireNonNull(genericEntity));
    }

    /**
     * @param entity
     * @param update
     */
    protected static void setTimestamp(GenericEntityI entity, boolean update) {
        if (update) {
            entity.setUpdatedAt(LocalDateTime.now());
        } else {
            LocalDateTime time = LocalDateTime.now();
            entity.setUpdatedAt(time);
            entity.setCreatedAt(time);
        }
    }
}
