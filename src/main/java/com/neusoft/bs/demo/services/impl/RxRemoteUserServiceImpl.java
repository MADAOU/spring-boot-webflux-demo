package com.neusoft.bs.demo.services.impl;

import com.neusoft.bs.demo.model.User;
import com.neusoft.bs.demo.services.RxUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RxRemoteUserServiceImpl implements RxUserService {

    private static final Logger log = LoggerFactory.getLogger(RxRemoteUserServiceImpl.class);

    private WebClient webclient;

    public RxRemoteUserServiceImpl(String url) {
        log.debug("remote service url: {}", url);
        this.webclient = WebClient.builder().baseUrl(url).build();
    }

    @Override
    public Mono<User> create(User one) {
        return webclient.post().uri("/users").contentType(MediaType.APPLICATION_JSON).body(Mono.just(one), User.class)
                .exchange().flatMap(clientResponse -> clientResponse.bodyToMono(User.class));
    }

    @Override
    public Mono<User> delete(User one) {
        return webclient.method(HttpMethod.DELETE).uri("/users/" + one.getId()).contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(one), User.class).exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(User.class));
    }

    @Override
    public Mono<User> update(User one) {
        return webclient.method(HttpMethod.PUT).uri("/users/" + one.getId()).contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(one), User.class).exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(User.class));
    }

    @Override
    public Flux<User> list() {
        return webclient.get().uri("/users").exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class));
    }

    @Override
    public Mono<User> findOne(String primaryKey) {
        return webclient.get().uri("/users/" + primaryKey).exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(User.class));
    }

    @Override
    public Flux<User> query(User condition) {
        return webclient.method(HttpMethod.GET).uri("/users/query").body(Mono.just(condition), User.class).exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(User.class));
    }

}