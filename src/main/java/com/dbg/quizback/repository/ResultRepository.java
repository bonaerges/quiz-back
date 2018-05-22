package com.dbg.quizback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dbg.quizback.model.Result;
import com.dbg.quizback.model.User;

public interface ResultRepository extends PagingAndSortingRepository<Result,Integer> {

	Page<Result> findByName(Pageable pageable, String name);
	Page<Result> findByUser(Pageable pageable, String name);
}
