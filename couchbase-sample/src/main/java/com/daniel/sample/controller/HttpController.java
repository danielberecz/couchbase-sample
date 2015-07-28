package com.daniel.sample.controller;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.daniel.sample.dto.User;
import com.daniel.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class HttpController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) throws JsonProcessingException {
        userService.saveUser(user);
    }

    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    public User getUsers(@PathVariable("id") String id) throws IOException {
        return userService.findOne(id);
    }

    @RequestMapping(value = "/user/name/{name}", method = RequestMethod.GET)
    public Iterable<User> getUser(@PathVariable("name") String lastName) {
        return userService.findByLastName(lastName);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }
}
