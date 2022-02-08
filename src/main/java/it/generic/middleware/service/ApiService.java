package it.generic.middleware.service;

import it.generic.middleware.model.GenericEntityI;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@UtilityClass
public class ApiService {

    /**
     *
     * @param body
     * @return
     */
    public static Mono<ServerResponse> okResponse(@NonNull final Object body) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body);
    }

    /**
     *
     * @param body
     * @return
     */
    public static Mono<ServerResponse> entityCreated(@NonNull final GenericEntityI body) {
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body);
    }

    /**
     *
     * @param object
     * @return
     */
    public static Mono<?> entityNotFound(@NonNull final GenericEntityI object){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    /**
     *
     * @param object
     * @return
     */
    public static Mono<?> entityConflict(@NonNull final GenericEntityI object){
        return Mono.error(new ResponseStatusException(HttpStatus.CONFLICT));
    }



}
