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

import com.dbg.quizback.dao.ResultDAO;
import com.dbg.quizback.model.Result;

@Service
public class ResultServiceImpl implements ResultService {

	private static final Logger logger= LoggerFactory.getLogger(ResultServiceImpl.class);
	
	@Autowired
	ResultDAO resultDAO;
 
	@Override
	public Result create(Result t) {
		Result resultObject=resultDAO.save(t);
		logger.info(" Result create successfully " + t.toString());
		return resultObject;
	}

	@Override
	public void update(Result t) {
		resultDAO.save(t);
		logger.info(" Result update successfully " + t.toString());
		
	}
	@Override
	public void delete(Result t) {
		resultDAO.delete(t);
		logger.info(" Result delete successfully " + t.toString());
		
	}
	@Override
	public Optional<Result> findById(Integer id){	
		Optional <Result> resultObject=resultDAO.findById(id);		
		logger.info(" Result findById successfully " + resultObject.toString());
		return resultObject;
	}
	
	@Override
	public Set<Result> findAll(Pageable p){	
		int page=p.getPageNumber();
		int size=p.getPageSize();
		return resultDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());		
	}
	
	
	
}
