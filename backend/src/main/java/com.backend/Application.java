package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by alinaaleksandrova on 3/5/17.
 */
@SpringBootApplication(scanBasePackages = {"com.backend", "com.persistence"})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


}
