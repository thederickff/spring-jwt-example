package com.github.thederickff.jwtexample.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.github.thederickff.jwtexample.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByEmail(String email);

}
