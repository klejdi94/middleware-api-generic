package it.generic.middleware.config;

import com.fasterxml.jackson.databind.MapperFeature;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApiConfig {

    /**
     *
     */
    public static final String VERSION_PREFIX = "{version}";

    /**
     *
     */
    @Value("${middleware.api.ver}")
    private String apiVersion;

    /**
     *
     */
    @Value("${middleware.api.path}")
    @Getter
    private String apiPath;



    /**
     *
     */
    @PostConstruct
    private void replace(){
        apiPath = apiPath.replace(VERSION_PREFIX,apiVersion);
    }


    /**
     *
     * @return
     */
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jsonCostumizer(){
        return builder -> builder.featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }


}
