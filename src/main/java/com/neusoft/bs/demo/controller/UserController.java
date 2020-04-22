package com.neusoft.bs.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.bs.demo.model.User;
import com.neusoft.bs.demo.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @PostMapping("")
    public User addUser(@RequestBody final User user) {
        log.debug("user : {}", user);

        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") final String id, @RequestBody final User user) {
        log.debug("id: {}, user : {}", id, user);

        return userService.update(user.setId(id));
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") final String id) {
        log.debug("id : {}", id);

        return userService.delete(new User().setId(id));
    }

    @GetMapping("")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/query")
    public List<User> query(final User user) {
        log.debug("user : {}", user);

        return userService.query(user);
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") final String id){
        log.debug("id : {}", id);
        User user = userService.findOne(id);

        if(user == null) {
            throw new ResourceNotFoundException("user not found.");
        }

        return user;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Object notfound(ResourceNotFoundException e){
        Map<String, String> message = new HashMap<>();
        message.put("message", e.getMessage());
        message.put("code", String.valueOf(HttpStatus.NOT_FOUND.value()));

        return message;
    }
}