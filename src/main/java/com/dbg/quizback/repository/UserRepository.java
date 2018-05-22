package com.dbg.quizback.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dbg.quizback.model.User;

public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

	Page<User> findByName(Pageable pageable, String name);
	 // Enabling ignoring case for an individual property
	Page<User> findByNameIgnoreCase(Pageable pageable,String username);
	Page<User> findByResult(Pageable pageable, String name);
}
