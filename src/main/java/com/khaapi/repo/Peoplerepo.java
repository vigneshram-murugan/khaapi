package com.khaapi.repo;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.khaapi.model.person;


public interface Peoplerepo extends CrudRepository<person, String> {

}