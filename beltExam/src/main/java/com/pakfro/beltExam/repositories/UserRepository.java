package com.pakfro.beltExam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pakfro.beltExam.models.User;

public interface UserRepository extends CrudRepository <User, Long>{
	User findByEmail(String email);
	List<User> findAll();


}
