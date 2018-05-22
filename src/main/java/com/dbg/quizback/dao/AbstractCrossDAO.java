package com.dbg.quizback.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface AbstractCrossDAO< T > extends PagingAndSortingRepository<T, Integer> { 

	

}
