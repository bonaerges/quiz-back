package com.dbg.quizback.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

public interface AbstractCrossService<T,ID extends Serializable> {

	
	T create(T t);
	void update(T t);
	void delete(T t);
	Optional<T> findById(Integer id);
	Set<T> findAll(Pageable p);

}
