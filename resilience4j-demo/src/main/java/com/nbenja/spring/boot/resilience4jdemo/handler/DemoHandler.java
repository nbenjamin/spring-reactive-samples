package com.nbenja.spring.boot.resilience4jdemo.handler;

import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class DemoHandler {

    public Mono<ServerResponse> demo(ServerRequest sr) {
        return ok().body(fromObject("Hello Resilient4J"));
    }
}
