package com.neusoft.bs.demo.controller;

import com.neusoft.bs.demo.model.User;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/sse")
public class UserSSEController {
    @GetMapping("/sync")
    public Flux<ServerSentEvent<User>> userModifiedNotify() {
        // return Flux.interval(Duration.ofSeconds(1))
        // .map(seq ->Tuples.of(seq,  ThreadLocalRandom.current().nextInt()))
        // .map(data->ServerSentEvent.builder().event("his"));

        return null;

    }
}