package com.daniel.sample.service;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonParseException;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.daniel.sample.dto.User;

import java.io.IOException;

public interface UserService {

    void saveUser(User user) throws JsonProcessingException;

    User findOne(String id) throws IOException;

    Iterable<User> findByLastName(String lastName);

    Iterable<User> getUsers();

}
