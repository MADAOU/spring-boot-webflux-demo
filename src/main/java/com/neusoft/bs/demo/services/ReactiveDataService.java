package com.neusoft.bs.demo.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveDataService<T> {

    Mono<T> create(T one);

    Mono<T> delete(T one);

    Mono<T> update(T one);

    Flux<T> list();

    Mono<T> findOne(String primaryKey);

    Flux<T> query(T condition);

}