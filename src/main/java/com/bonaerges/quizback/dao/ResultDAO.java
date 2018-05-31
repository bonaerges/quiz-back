package com.bonaerges.quizback.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bonaerges.quizback.model.Result;

@Repository
public interface ResultDAO extends PagingAndSortingRepository<Result,Integer > {


}
