package com.bonaerges.quizback.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.ResultDAO;
import com.bonaerges.quizback.model.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultServiceImpl implements ResultService {

	
	@Autowired
	ResultDAO resultDAO;
 
	@Override
	public Result create(Result t) {
		Result resultObject=resultDAO.save(t);
		log.info(" Result create successfully " + t.toString());
		return resultObject;
	}

	@Override
	public void update(Result t) {
		resultDAO.save(t);
		log.info(" Result update successfully " + t.toString());
		
	}
	@Override
	public void delete(Result t) {
		resultDAO.delete(t);
		log.info(" Result delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Result> findById(Integer id){	
		Optional <Result> resultObject=resultDAO.findById(id);		
		log.info(" Result findById successfully " + resultObject.toString());
		return resultObject;
	}
	
	@Override
	public Set<Result> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return resultDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	
	
}
