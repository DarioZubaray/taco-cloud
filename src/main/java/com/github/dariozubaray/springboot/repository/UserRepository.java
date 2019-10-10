package com.github.dariozubaray.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import com.github.dariozubaray.springboot.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
