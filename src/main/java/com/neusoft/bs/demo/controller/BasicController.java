package com.neusoft.bs.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hello/")
public class BasicController {

    @GetMapping("/{name}")
    public Mono<String> hello(@PathVariable("name") final String name) {
        return Mono.just(String.format("hello %s", name));
    }
}