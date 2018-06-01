package com.bonaerges.quizback.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

public interface AbstractCrossService<T,ID extends Serializable> {

	
	T create(T t);
	void update(T t);
	void delete(T t);
	Optional<T> findById(ID id);
	List<T> findAll(Pageable p);

}
