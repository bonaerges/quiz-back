package com.dbg.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dbg.quizback.dao.TagDAO;
import com.dbg.quizback.model.Tag;

@Service
public class TagServiceImpl implements TagService {

	private static final Logger logger= LoggerFactory.getLogger(TagServiceImpl.class);
	
	@Autowired
	TagDAO tagDAO;
 
	@Override
	public Tag create(Tag t) {
		Tag tagObject=tagDAO.save(t);
		logger.info(" Tag create successfully " + t.toString());
		return tagObject;
	}

	@Override
	public void update(Tag t) {
		tagDAO.save(t);
		logger.info(" Tag update successfully " + t.toString());
		
	}
	@Override
	public void delete(Tag t) {
		tagDAO.delete(t);
		logger.info(" Tag delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Tag> findById(Integer id){	
		Optional <Tag> tagObject=tagDAO.findById(id);		
		logger.info(" Tag findById successfully " + tagObject.toString());
		return tagObject;
	}
	
	@Override
	public Set<Tag> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return tagDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	public Optional<Tag> findOneByNameOrderByIdDesc(String name){
		Optional <Tag> tagObject=tagDAO.findOneByNameOrderByIdDesc(name);
		tagObject.ifPresent(t ->logger.info("Tag findOneByNameOrderByIdTagDesc "  + t.toString()));
		return tagObject;
		
	}

	
}
