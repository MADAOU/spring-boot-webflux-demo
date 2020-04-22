package com.neusoft.bs.demo.services.impl;

import java.util.Map;

import com.neusoft.bs.demo.model.User;
import com.neusoft.bs.demo.services.RxUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RxUserServiceImpl implements RxUserService {

    private static final Logger log = LoggerFactory.getLogger(RxUserServiceImpl.class);

    private final Map<String, User> data;

    public RxUserServiceImpl(Map<String, User> data) {
        this.data = data;
    }

    @Override
    public Mono<User> create(final User user) {
        return Mono.justOrEmpty(data.put(user.getId(), user)).flatMap(p -> this.findOne(user.getId()));
    }

    @Override
    public Mono<User> delete(final User user) {
        return Mono.justOrEmpty(this.data.remove(user.getId()));
    }

    @Override
    public Mono<User> update(final User user) {
        return Mono.justOrEmpty(this.data.put(user.getId(), user)).map(p->user);
    }

    @Override
    public Flux<User> list() {
        return Flux.fromIterable(this.data.values());
    }

    @Override
    public Mono<User> findOne(final String primaryKey) {
        return Mono.justOrEmpty(this.data.get(primaryKey));
    }

    @Override
    public Flux<User> query(final User condition) {
        return Flux.fromIterable(this.data.values()).filter(user -> {
            log.debug("user : {}", user);

            boolean check = condition.getAge() == user.getAge() || (condition.getName() != null
                    ? condition.getName().equals(user.getName())
                    : false);

            return check;
        });
    }

}