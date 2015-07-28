package com.daniel.sample.service;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.dsl.Expression;
import com.daniel.sample.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.couchbase.client.java.query.Select.select;

//http://blog.couchbase.com/n1ql-dp4-java-sdk
//http://docs.couchbase.com/developer/java-2.1/querying-n1ql.html

@Component
@Profile("couch-nickel")
public class UserServiceNickel implements UserService {

    @Autowired
    private Bucket bucket;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveUser(User user) throws JsonProcessingException {
        JsonObject userJson = JsonObject.fromJson(objectMapper.writeValueAsString(user));

        bucket.insert(JsonDocument.create(user.getId(), userJson));
    }

    @Override
    public User findOne(String id) throws IOException {
        String user = bucket.query(select("*").from("default").where(Expression.x("id").eq(Expression.s(id))))
                .allRows().stream().findFirst().get().value().getObject("default").toString();

        return objectMapper.readValue(user, User.class);
    }

    @Override
    public Iterable<User> findByLastName(String lastName) {
        return bucket.query(select("*").from("default").where(Expression.x("lastName").eq(Expression.s(lastName))))
                .allRows().stream().map(e -> getUserFromJson(e.value().getObject("default").toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<User> getUsers() {
        return bucket.query(select("*").from("default"))
                .allRows().stream().map(e -> getUserFromJson(e.value().getObject("default").toString()))
                .collect(Collectors.toList());
    }

    private User getUserFromJson(String userJson) {
        try {
            return objectMapper.readValue(userJson, User.class);
        } catch (IOException e) {
            return new User();
        }
    }
}
