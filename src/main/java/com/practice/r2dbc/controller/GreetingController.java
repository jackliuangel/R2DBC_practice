package com.practice.r2dbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

public class GreetingController {
    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Mono<Greeting> greeting(@RequestParam(required = false, defaultValue = "World") String name) {
        return Mono.just(new Greeting(counter.incrementAndGet(), String.format(template, name)));
    }
}
