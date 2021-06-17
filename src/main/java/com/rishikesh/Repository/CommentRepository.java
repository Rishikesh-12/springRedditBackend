package com.rishikesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishikesh.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
