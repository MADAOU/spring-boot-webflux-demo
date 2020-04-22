package com.neusoft.bs.demo.services.impl;

import java.util.List;

import com.neusoft.bs.demo.model.User;
import com.neusoft.bs.demo.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RemoteUserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(RemoteUserServiceImpl.class);

    private String userServiceURL;

    private RestTemplate restTemplate;

    public RemoteUserServiceImpl(String url) {
        log.debug("remote service url: {}", url);
        this.userServiceURL = url;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public User create(User one) {
        String url = new StringBuilder(this.userServiceURL).append("/users/").toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(one, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, User.class).getBody();
    }

    @Override
    public User delete(User one) {
        String url = new StringBuilder(this.userServiceURL).append("/users/").append(one.getId()).toString();

        return restTemplate.exchange(url, HttpMethod.DELETE, null, User.class).getBody();
    }

    @Override
    public User update(User one) {
        String url = new StringBuilder(this.userServiceURL).append("/users/").append(one.getId()).toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(one, headers);

        return restTemplate.exchange(url, HttpMethod.PUT, entity, User.class).getBody();
    }

    @Override
    public List<User> list() {
        String url = new StringBuilder(this.userServiceURL).append("/users").toString();

        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

    @Override
    public User findOne(String primaryKey) {
        String url = new StringBuilder(this.userServiceURL).append("/users/").append(primaryKey).toString();
        return restTemplate.exchange(url, HttpMethod.GET, null, User.class).getBody();
    }

    @Override
    public List<User> query(User condition) {
        String url = new StringBuilder(this.userServiceURL).append("/users/query").toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(condition, headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }

}