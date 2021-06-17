package com.rishikesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishikesh.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
