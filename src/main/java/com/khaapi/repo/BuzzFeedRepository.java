package com.khaapi.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khaapi.model.BuzzFeed;
public interface BuzzFeedRepository extends JpaRepository<BuzzFeed, Long> {
	
	List<BuzzFeed> findAll();

}
