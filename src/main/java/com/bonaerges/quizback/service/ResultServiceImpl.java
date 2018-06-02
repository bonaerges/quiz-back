package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.component.mapper.questionnaireUserAnswer.QuestionnaireUserAnswerMapper;
import com.bonaerges.quizback.dao.ResultDAO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO resultDAO;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionnaireService questionnaireService;
	
	@Autowired
	QuestionnaireUserAnswerService questionnaireUserAnswerService;

	@Autowired
	QuestionnaireUserAnswerMapper questionnaireUserAnswerMapper;

	@Override
	public Result create(Result t) {
		Result resultObject = resultDAO.save(t);
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
	public Optional<Result> findById(Integer id) {
		Optional<Result> resultObject = resultDAO.findById(id);
		log.info(" Result findById successfully " + resultObject.toString());
		return resultObject;
	}

	@Override
	public List<Result> findAll(Pageable p) {
		int page = p.getPageNumber();
		int size = p.getPageSize();
		return resultDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
	}
	
	public Optional<Course> getCourse(Integer idCourse) throws NotFoundException {
		return courseService.findById(idCourse);	
	}

	public Optional<Questionnaire> getQuestionnaire(Integer idQuestionnaire) throws NotFoundException{
		
		return  questionnaireService.findById(idQuestionnaire);
		
	}

	@Override
	public List<Result> findAllByQuestionnarie(Integer idQuest) {
		return resultDAO.findAllByQuestionnarie(idQuest);
	}
	
}
