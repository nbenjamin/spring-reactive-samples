package com.nbenja.spring.boot.resilience4jdemo.config;

import com.nbenja.spring.boot.resilience4jdemo.handler.DemoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Resilience4jConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(DemoHandler demoHandler) {
        return route(GET("/demo"), demoHandler::demo);
    }

    @Bean
    ReactiveCir

}
