package it.generic.middleware.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface GenericEntityI extends Serializable {

    /**
     *
     * @return
     */
    String getId();

    /**
     *
     * @return
     */
    void setId(String id);

    /**
     *
     * @return
     */
    LocalDateTime getCreatedAt();

    /**
     *
     * @return
     */
    LocalDateTime getUpdatedAt();

    /**
     *
     * @param createdAt
     */
    void setCreatedAt(LocalDateTime createdAt);

    /**
     *
     * @param updatedAt
     */
    void setUpdatedAt(LocalDateTime updatedAt);
}
