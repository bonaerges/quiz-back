package com.dbg.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.LevelDAO;
import com.dbg.quizback.model.Level;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LevelServiceImpl implements LevelService {

	
	@Autowired
	LevelDAO levelDAO;
 
	@Override
	public Level create(Level t) {
		Level levelObject=levelDAO.save(t);
		log.info(" Level create successfully " + t.toString());
		return levelObject;
	}

	@Override
	public void update(Level t) {
		levelDAO.save(t);
		log.info(" Level update successfully " + t.toString());
		
	}
	@Override
	public void delete(Level t) {
		levelDAO.delete(t);
		log.info(" Level delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Level> findById(Integer id){	
		Optional <Level> levelObject=levelDAO.findById(id);		
		log.info(" Level findById successfully " + levelObject.toString());
		return levelObject;
	}
	
	@Override
	public Set<Level> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return levelDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<Level> findOneByNameOrderByIdDesc(String name){
		Optional <Level> levelObject=levelDAO.findOneByNameOrderById(name);
		levelObject.ifPresent(t ->log.info("Level findOneByNameOrderById "  + t.toString()));
		return levelObject;
		
	}

	
}
