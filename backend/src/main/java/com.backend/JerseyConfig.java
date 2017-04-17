package com.backend;

import com.persistence.service.TodoService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(TodoService service) {
        register(CORSFilter.class);
        registerEndpoints(service);
    }

    private void registerEndpoints(TodoService service) {
         register(TodoBackendEndpoint.class);
         register(new AbstractBinder() {
             @Override
             protected void configure() {
                 bind(service).to(TodoService.class);
             }
         });
    }
}