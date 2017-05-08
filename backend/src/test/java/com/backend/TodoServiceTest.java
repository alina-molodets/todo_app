package com.backend;

import com.persistence.service.TodoService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by alinaaleksandrova on 4/17/17.
 */
@Profile("test")
@Configuration
public class TodoServiceTest {

    @Bean
    @Primary
    public TodoService todoService() {
        return Mockito.mock(TodoService.class);
    }


}
