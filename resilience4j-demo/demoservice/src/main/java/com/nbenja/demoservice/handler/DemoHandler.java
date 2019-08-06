package com.nbenja.demoservice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class DemoHandler {

    public Mono<ServerResponse> demo(ServerRequest sr) {
        long delay = (long) (Math.random() * 5);
        System.out.println(delay);
        return ok().body(fromObject("Hello Resilient4J Demo Service"))
                .delayElement(Duration.ofSeconds(delay));
    }
}
