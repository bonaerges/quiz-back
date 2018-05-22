package com.dbg.quizback.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.dbg.quizback.model.Tag;

@Repository
public interface TagDAO extends AbstractCrossDAO<Tag> {

	Optional<Tag> findOneByNameOrderByIdDesc(String name);

}
