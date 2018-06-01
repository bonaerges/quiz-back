package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.TagDAO;
import com.bonaerges.quizback.model.Tag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TagServiceImpl implements TagService {

	
	
	@Autowired
	TagDAO tagDAO;
 
	@Override
	public Tag create(Tag t) {
		Tag tagObject=tagDAO.save(t);
		log.info(" Tag create successfully " + t.toString());
		return tagObject;
	}

	@Override
	public void update(Tag t) {
		tagDAO.save(t);
		log.info(" Tag update successfully " + t.toString());
		
	}
	@Override
	public void delete(Tag t) {
		tagDAO.delete(t);
		log.info(" Tag delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Tag> findById(Integer id){	
		Optional <Tag> tagObject=tagDAO.findById(id);		
		log.info(" Tag findById successfully " + tagObject.toString());
		return tagObject;
	}
	
	@Override
	public List<Tag> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return tagDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());		
	}
	
	public Optional<Tag> findOneByNameOrderByIdDesc(String name){
		Optional <Tag> tagObject=tagDAO.findByNameOrderByIdDesc(name);
		tagObject.ifPresent(t ->log.info("Tag findOneByNameOrderByIdTagDesc "  + t.toString()));
		return tagObject;
		
	}

	
}
