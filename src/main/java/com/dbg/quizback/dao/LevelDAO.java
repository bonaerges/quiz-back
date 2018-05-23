package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Level;

@Repository
public interface LevelDAO extends PagingAndSortingRepository<Level, Integer>{

	Optional<Level> findOneByNameOrderById(String name);

}
