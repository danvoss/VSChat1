package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dan on 7/24/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}
