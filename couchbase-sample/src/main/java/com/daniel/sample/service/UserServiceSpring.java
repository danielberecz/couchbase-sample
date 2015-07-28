package com.daniel.sample.service;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.daniel.sample.dao.UserRepository;
import com.daniel.sample.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("couch-spring")
public class UserServiceSpring implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<User> findByLastName(String lastName) {
        Query query = new Query();
        query.setKey(ComplexKey.of(lastName));

        return userRepository.findByLastName(query);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
