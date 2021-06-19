package com.rishikesh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishikesh.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String userName);

}
