package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbg.quizback.model.Result;

@Repository
public interface ResultDAO extends PagingAndSortingRepository<Result,Integer > {


}
