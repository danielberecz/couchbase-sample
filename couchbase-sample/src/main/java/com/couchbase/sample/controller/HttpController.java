package com.couchbase.sample.controller;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.sample.dao.UserRepository;
import com.couchbase.sample.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "/user/id/{id}", method = RequestMethod.GET)
    public User getUsers(@PathVariable("id") String id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/user/name/{name}", method = RequestMethod.GET)
    public Iterable<User> getUser(@PathVariable("name") String lastName) {
        Query query = new Query();
        query.setKey(ComplexKey.of(lastName));

        return userRepository.findByLastName(query);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
