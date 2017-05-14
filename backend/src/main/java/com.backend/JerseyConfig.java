package com.backend;

import com.persistence.service.TodoService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(TodoService service) {
        register(CORSFilter.class);
        registerEndpoints(service);

        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, null));

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