package com.bonaerges.quizback.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.answer.AnswerMapper;
import com.bonaerges.quizback.component.mapper.question.QuestionMapper;
import com.bonaerges.quizback.component.mapper.question.QuestionUpdateDTOMapper;
import com.bonaerges.quizback.dto.AnswerDTO;
import com.bonaerges.quizback.dto.QuestionDTO;
import com.bonaerges.quizback.dto.QuestionUpdateDTO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Answer;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.service.AnswerService;
import com.bonaerges.quizback.service.QuestionService;

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
	QuestionUpdateDTOMapper questionUpdateDTOMapper;
	
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

	@RequestMapping(value = "/{id}/showCorrectAnswer", method = RequestMethod.GET)
	public ResponseEntity<AnswerDTO> getCorrectAnswer(@PathVariable Integer idQuestion) {
		Optional<Question> question = questionService.findById(idQuestion);
		ResponseEntity<AnswerDTO> respEnt=new ResponseEntity<AnswerDTO>(HttpStatus.OK);
		if (question.isPresent()) {
			Answer answerCorrect=
					 question.get().getAnswer().stream()
					 .filter(ans->ans.getIsCorrect() == true).findFirst().get();
			 	log.info("Answer Correct " + answerCorrect.getDescription() + " found for question id "+ idQuestion);
				respEnt=new ResponseEntity<AnswerDTO>(answerMapper.modelToDto(answerCorrect),HttpStatus.OK);
		}
		else respEnt=new ResponseEntity<AnswerDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}
	/************************************HTTP METHOD POST *************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)	
	//Only allow create Question not mapped objects
	public ResponseEntity<QuestionUpdateDTO> create(@RequestBody QuestionUpdateDTO dto) {
		Question createQuestion = questionService.create(questionUpdateDTOMapper.dtoToModel(dto));		
		log.info("Question " + createQuestion.getId() + " succesfuly created.");
		log.warn("Pending to create answers linked to question");
		return new ResponseEntity<QuestionUpdateDTO>(questionUpdateDTOMapper.modelToDto(createQuestion),HttpStatus.OK);
	}
	// url-->/question/(idQuestion)/answer/(idAnswer)

	/************************************HTTP METHOD PUT *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	//allow update existing question creating answers, tag and Level
	ResponseEntity<QuestionUpdateDTO> update(@PathVariable("id") Integer id, @RequestBody QuestionUpdateDTO dto) {

		Optional<Question> questionModel=questionService.findById(id);
		ResponseEntity<QuestionUpdateDTO> respEnt=new ResponseEntity<QuestionUpdateDTO>(HttpStatus.NOT_FOUND);
		if(questionModel.isPresent()) {
			questionModel.get().setId(id);
			Question questionModelDTO=questionUpdateDTOMapper.dtoToModel(dto);
			questionService.update(questionModel.get());  
			
			respEnt=new ResponseEntity<QuestionUpdateDTO>(dto,HttpStatus.OK);
		}
		else respEnt=new ResponseEntity<QuestionUpdateDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/answer",method = {RequestMethod.PUT})
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
	
	@ResponseBody
	@RequestMapping(value="/{id}/answers",method = {RequestMethod.PUT})
	public ResponseEntity<List<AnswerDTO>> updateAnswersQuestion(
			@PathVariable("id") Integer id,
			@RequestBody List<AnswerDTO> dto) throws NotFoundException {

		List<Answer> answerModel = answerMapper.dtoToModel(dto) ;
		//For idQuestion  save new answer, then, update question
		answerModel.forEach(ans -> {
			
			answerService.addAnswerQuestion(ans,id);
			ans.getQuestion().setId(id);
			//Update question object
			questionService.update(ans.getQuestion()); 
		});
	
		
		return new ResponseEntity<List<AnswerDTO>>(dto,HttpStatus.OK);
	}	
	
	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<QuestionDTO> delete(@PathVariable("id") Integer id) {		
		Optional<Question> question=questionService.findById(id);
		ResponseEntity<QuestionDTO> respEnt=respEntOK;
		if(question.isPresent()) {
			//delete all answers linked to current question 
			question.get().getAnswer().forEach((final Answer answerLink)->answerService.delete(answerLink));
			questionService.delete(question.get());   
			respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.OK);
			log.info("Succesfully delete question " + id + " and answers linked to question"); 
		}
		else {
			respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.NOT_FOUND);
		}
		return respEnt;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/answer/{idA}",method = {RequestMethod.DELETE})
	public ResponseEntity<QuestionDTO> deleteAnswerQuestion(
			@PathVariable("id") Integer id,
			@PathVariable("idA") Integer idA) throws NotFoundException {
		Optional<Question> question=questionService.findById(id);
		ResponseEntity<QuestionDTO> respEnt=respEntOK;
		if (question.isPresent()) {
			answerService.deleteAnswerQuestion(idA,id);
			questionService.update(question.get());
			respEnt=new ResponseEntity<QuestionDTO>(questionMapper.modelToDto(question.get()),HttpStatus.OK);
			log.info("Succesfully delete answer "+ idA + " linked to question " + id ); 
		}
		else respEnt=new ResponseEntity<QuestionDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}	
	
	@ResponseBody
	@RequestMapping(value="/{id}/answers",method = {RequestMethod.DELETE})
	public ResponseEntity<List<QuestionDTO>> deleteAnswersQuestion(
			@PathVariable("id") Integer id) throws NotFoundException {
		Optional<Question> question=questionService.findById(id);
		ResponseEntity<List<QuestionDTO>> respEnt;
		if (question.isPresent()) {
			//For idQuestion  save new answer, then, update question
			question.get().getAnswer().forEach(ans -> {
				answerService.deleteAnswerQuestion(ans.getId(),id);
				ans.getQuestion().setId(id);
				//Update question object
				questionService.update(ans.getQuestion()); 
			});
			respEnt=new ResponseEntity<List<QuestionDTO>>(HttpStatus.OK);
			log.info("Succesfully delete answers linked to question " + id ); 
		}
		else respEnt=new ResponseEntity<List<QuestionDTO>>(HttpStatus.NOT_FOUND);
		return respEnt;
	}	
}
