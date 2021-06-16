package com.pakfro.beltExam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pakfro.beltExam.models.Idea;

public interface IdeaRepository extends CrudRepository <Idea, Long> {
	List<Idea> findAll();

}
