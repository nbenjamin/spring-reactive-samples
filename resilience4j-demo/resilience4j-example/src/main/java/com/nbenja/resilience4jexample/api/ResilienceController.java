package com.nbenja.resilience4jexample.api;

import com.nbenja.resilience4jexample.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ResilienceController {

    private DemoService demoService;

    public ResilienceController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/resilience")
    public Mono<String> resilience() {
        return  demoService.demo();
    }
}
