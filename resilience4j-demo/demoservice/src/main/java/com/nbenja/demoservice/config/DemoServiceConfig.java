package com.nbenja.demoservice.config;

import com.nbenja.demoservice.handler.DemoHandler;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DemoServiceConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(DemoHandler demoHandler) {
        return route(GET("/demo"), demoHandler::demo);
    }


}
