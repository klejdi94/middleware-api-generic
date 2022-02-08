package it.generic.middleware;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiddlewareApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MiddlewareApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
