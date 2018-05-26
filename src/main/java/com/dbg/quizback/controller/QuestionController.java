package com.dbg.quizback.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.answer.AnswerMapper;
import com.dbg.quizback.component.mapper.question.QuestionMapper;
import com.dbg.quizback.dto.AnswerDTO;
import com.dbg.quizback.dto.QuestionDTO;
import com.dbg.quizback.dto.UserDTO;
import com.dbg.quizback.exception.NotFoundException;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Question;
import com.dbg.quizback.service.AnswerService;
import com.dbg.quizback.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/question")
public class QuestionController {

	final ResponseEntity<QuestionDTO> respEntOK=new ResponseEntity<QuestionDTO>(HttpStatus.OK);
	final ResponseEntity<QuestionDTO> respEntNotFound=new ResponseEntity<QuestionDTO>(HttpStatus.NOT_FOUND);
	
	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionMapper questionMapper;

	@Autowired
	AnswerMapper answerMapper;


	/************************************HTTP METHOD GET *************************************/

	@ResponseBody
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<QuestionDTO>  findById(
			@PathVariable("id") Integer id){
		Optional<Question> questionModel=questionService.findById(id);
		ResponseEntity<QuestionDTO> respEnt=respEntOK;
		if(questionModel.isPresent()) {
			log.info("Question " + id + " found");
			respEnt=new ResponseEntity<QuestionDTO>(questionMapper.modelToDto(questionModel.get()),HttpStatus.OK);
		}
		else respEnt=respEntNotFound;
		return respEnt;
	}


	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Set<QuestionDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Question> questions=questionService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll questions count is: "+ Integer.toString(questions.size()));
		return questionMapper.modelToDto(questions);
	}

	/************************************HTTP METHOD POST *************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO dto) {
		Question createQuestion = questionService.create(questionMapper.dtoToModel(dto));		
		log.info("Question " + createQuestion.getId() + " succesfuly created.");
		log.warn("Pending to create answers linked to question");
		return new ResponseEntity<QuestionDTO>(questionMapper.modelToDto(createQuestion),HttpStatus.OK);
	}
	// url-->/question/(idQuestion)/answer/(idAnswer)

	/************************************HTTP METHOD PUT *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<QuestionDTO> update(@PathVariable("id") Integer id, @RequestBody QuestionDTO dto) {

		Optional<Question> questionModel=questionService.findById(id);
		ResponseEntity<QuestionDTO> respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.OK);
		if(questionModel.isPresent()) {
			questionModel.get().setId(id);
			questionService.update(questionModel.get());  
			respEnt=new ResponseEntity<QuestionDTO>(dto,HttpStatus.OK);
		}
		else respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	@ResponseBody
	@RequestMapping(value="/{id}/answer",method = {RequestMethod.PUT,RequestMethod.POST})
	public ResponseEntity<QuestionDTO> updateAnswerQuestion(
			@PathVariable("id") Integer id,
			@RequestBody AnswerDTO dto) throws NotFoundException {

		Answer answerModel = answerMapper.dtoToModel(dto) ;
		//For idQuestion  save new answer, then, update question
		answerService.addAnswerQuestion(answerModel,id);
		answerModel.getQuestion().setId(id);
		//Update question object
		questionService.update(answerModel.getQuestion()); 
		return new ResponseEntity<QuestionDTO>(questionMapper.modelToDto(answerModel.getQuestion()),HttpStatus.OK);
	}	
	
	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<QuestionDTO> delete(@PathVariable("id") Integer id, @RequestBody QuestionDTO dto) {		
		Optional<Question> question=questionService.findById(id);
		ResponseEntity<QuestionDTO> respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.OK);
		if(question.isPresent()) {
			//delete all answers linked to current question 
			question.get().getAnswer().forEach((final Answer answerLink)->answerService.delete(answerLink));
			questionService.delete(questionMapper.dtoToModel(dto));   
			respEnt=new ResponseEntity<QuestionDTO>(dto,HttpStatus.OK);
			log.info("Succesfully delete question " + id + " and answers linked to question"); 
		}
		else {
			respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.NOT_FOUND);
		}
		return respEnt;

	}
}
