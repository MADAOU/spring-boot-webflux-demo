package com.neusoft.bs.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.neusoft.bs.demo.model.User;
import com.neusoft.bs.demo.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private Map<String, User> data;

    public UserServiceImpl(Map<String, User> data) {
        this.data = data;
    }

    @Override
    public User create(User one) {
        data.put(one.getId(), one);
        return one;
    }

    @Override
    public User delete(User one) {
        return data.remove(one.getId());
    }

    @Override
    public User update(User one) {
        data.put(one.getId(), one);
        return one;
    }

    @Override
    public List<User> list() {
        return Arrays.asList(this.data.values().toArray(new User[this.data.size()]));
    }

    @Override
    public User findOne(String primaryKey) {
        return this.data.get(primaryKey);
    }

    @Override
    public List<User> query(User condition) {
        List<User> users = new ArrayList<>();

        this.data.values().forEach(user -> {
            log.debug("user : {}", user);

            boolean hit = condition.getAge() == user.getAge()
                    || (condition.getName() != null ? condition.getName().equals(user.getName()) : false);

            if (hit) {
                users.add(user);
            }
        });

        return users;
    }

}