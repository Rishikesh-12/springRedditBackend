package com.rishikesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishikesh.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
