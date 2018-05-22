package com.dbg.quizback.dao;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public abstract interface AbstractCrossDAO< T , ID extends Serializable>  { 
	
	T create(T t);
	void update(T t);
	void add(ID i);
	void delete(T t);
	Optional<T> findById(ID i);
	
	

}
