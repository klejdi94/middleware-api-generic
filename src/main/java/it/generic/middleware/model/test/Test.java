package it.generic.middleware.model.test;

import it.generic.middleware.model.GenericEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document("test")
public class Test extends GenericEntity {

    /**
     *
     */
    private String nome;
    /**
     *
     */
    private String cognome;

}
