package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.dbg.quizback.model.Tag;

@Repository
public interface TagDAO extends PagingAndSortingRepository<Tag, Integer >{

	Optional<Tag> findOneByNameOrderByIdDesc(String name);

}
