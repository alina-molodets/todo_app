package com.backend;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

/**
 * Created by alinaaleksandrova on 3/5/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }


}
