package com.nbenja.resilience4jexample.service;

import org.springframework.cloud.circuitbreaker.commons.ReactiveCircuitBreaker;
import org.springframework.cloud.circuitbreaker.commons.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@Component
public class DemoService {

    private ReactiveCircuitBreaker reactiveCircuitBreaker;

    public DemoService(ReactiveCircuitBreakerFactory factory) {
        this.reactiveCircuitBreaker = factory.create("demo");
    }

    public Mono<String> demo() {

        return this.reactiveCircuitBreaker.run(WebClient.builder()
                .baseUrl("http://localhost:8086")
                .build()
                .get()
                .uri("/demo")
                .retrieve().bodyToMono(String.class), throwable -> Mono.just("Service failure"));
    }
}
