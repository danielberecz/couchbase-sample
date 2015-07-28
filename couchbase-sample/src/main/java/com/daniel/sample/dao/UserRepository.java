package com.daniel.sample.dao;

import com.couchbase.client.protocol.views.Query;
import com.daniel.sample.dto.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByLastName(Query query);

}
