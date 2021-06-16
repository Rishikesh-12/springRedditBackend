package com.rishikesh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishikesh.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
