package com.khaapi.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khaapi.model.Articles;

public interface ArticleRepo  extends JpaRepository<Articles, Long> {
	
	List<Articles> findAll();

}
