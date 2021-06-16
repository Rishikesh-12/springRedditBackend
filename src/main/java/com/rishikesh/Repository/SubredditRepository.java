package com.rishikesh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishikesh.model.Subreddit;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

}
