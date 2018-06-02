package com.bonaerges.quizback.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.bonaerges.quizback.component.mapper.question.QuestionMapper;
import com.bonaerges.quizback.component.mapper.questionnaire.QuestionnaireMapper;
import com.bonaerges.quizback.dto.AnswerDTO;
import com.bonaerges.quizback.dto.QuestionnaireDTO;
import com.bonaerges.quizback.dto.QuestionnaireQADTO;
import com.bonaerges.quizback.dto.QuestionnaireUserAnswerDTO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.service.QuestionnaireService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {

	@Autowired
	QuestionnaireService questionnaireService;

	@Autowired
	QuestionnaireMapper questionnaireMapper;

	@Autowired
	QuestionMapper questionMapper;


	final ResponseEntity<QuestionnaireDTO> respEntOK = new ResponseEntity<QuestionnaireDTO>(HttpStatus.OK);
	final ResponseEntity<QuestionnaireDTO> respEntNotFound = new ResponseEntity<QuestionnaireDTO>(HttpStatus.NOT_FOUND);

	/*************************************************************************/
	 /* HTTP METHOD GET
	 /**********************************************************************/
	/**
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	 
	// questionnaire/id
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<QuestionnaireQADTO> findById(@PathVariable("id") Integer id) throws NotFoundException {
		Optional<Questionnaire> questionnaireModel = questionnaireService.findById(id);
		ResponseEntity<QuestionnaireQADTO> respEnt = new ResponseEntity<QuestionnaireQADTO>(HttpStatus.OK);
		if (questionnaireModel.isPresent()) {
			QuestionnaireQADTO qADTO= new QuestionnaireQADTO();
			qADTO.setCourseDescription(questionnaireModel.get().getCourse().getDescription());
			questionnaireModel.get().getQuestion().forEach(q -> q.getAnswer());
			qADTO.setQuestion(questionMapper.modelToDto(questionnaireModel.get().getQuestion()));
			
			log.info("Questionnaire " + id + " found");
			respEnt = new ResponseEntity<QuestionnaireQADTO>(qADTO,HttpStatus.OK);
		}
		else {
		
			throw new NotFoundException("Must be created questionnaire first");
		}
		return respEnt;
	}

	// questionnaire?page=X&size=X
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<QuestionnaireDTO> findAll(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
		List<Questionnaire> questionnaires = questionnaireService.findAll(PageRequest.of(page, size)).stream()
				.collect(Collectors.toList());
		log.info("findAll questionnaires count is: " + Integer.toString(questionnaires.size()));
		return questionnaireMapper.modelToDto(questionnaires);
	}

	@ResponseBody
	@RequestMapping(value="/{id}/result",method = {RequestMethod.GET})
	public RedirectView getResultQuestionnarie(@PathVariable("idQ") Integer idQ,RedirectAttributes redirectAttributes)  {
		
	    RedirectView rv = new RedirectView();
        rv.setContextRelative(false);
        rv.setUrl("/result/{id}");
       
        return rv;
	}
	/************************************* HTTP METHOD POST	*************************************/
	/**
	 * 
	 * @param idCourse
	 * @param dto
	 * @return
	 * @throws NotFoundException
	 */
	// questionnaire
	@ResponseBody
	@RequestMapping(value ="/{idCourse}/questionnaire", method=RequestMethod.POST)
	public ResponseEntity<QuestionnaireDTO> create(@PathVariable("idCourse") Integer idCourse,@RequestBody QuestionnaireDTO dto) throws NotFoundException {
		
		final Questionnaire questionnaireModel = questionnaireMapper.dtoToModel(dto);
		final Optional<Course> course=questionnaireService.getCourse(idCourse);
		ResponseEntity<QuestionnaireDTO> respEnt;
		if (course.isPresent()) {
			questionnaireModel.setCourse(course.get());
			final Questionnaire createQuestionnaire = questionnaireService.create(questionnaireModel);
			log.info("Questionnaire " + createQuestionnaire.getId() + " succesfuly created.");
			respEnt = new ResponseEntity<QuestionnaireDTO>(questionnaireMapper.modelToDto(createQuestionnaire),HttpStatus.OK) ;
		}
		else respEnt=respEntNotFound;
		
		return respEnt;
	}

	/************************************* HTTP METHOD PUT	 *************************************/
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ExceptionHandler({ NotFoundException.class })
	ResponseEntity<QuestionnaireDTO> update(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) throws NotFoundException {

		final Optional<Questionnaire> questionnaireModel = questionnaireService.findById(id);
		ResponseEntity<QuestionnaireDTO> respEnt = respEntOK;
		if (questionnaireModel.isPresent()) {
			questionnaireService.update(questionnaireMapper.dtoToModel(dto));
		} else
			respEnt=respEntNotFound;
		return respEnt;
	}

	@ResponseBody
	@RequestMapping(path = "/{id}/saveAnswer", method = RequestMethod.PUT)
	public ResponseEntity<AnswerDTO> saveAnswer(@PathVariable("user") Integer idUser, @PathVariable("id") Integer id,
			QuestionnaireUserAnswerDTO qUADTO) {

		// Optional <Course> courseUser=courseService.findById(id);
		// if (courseUser.isPresent()) {
		// Optional <User> user=userService.findById(idUser);
		// courseUser.get().getUser().add(user.get());
		// courseService.update(courseUser.get());
		// return new
		// ResponseEntity<CourseDTO>(courseMapper.modelToDto(courseUser.get()),HttpStatus.OK);
		// }
		// else
		return new ResponseEntity<AnswerDTO>(HttpStatus.NOT_FOUND);

	}

	/************************************
	 * HTTP METHOD DELETE
	 *************************************/
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<QuestionnaireDTO> delete(@PathVariable("id") Integer id, @RequestBody QuestionnaireDTO dto) {
		final Optional<Questionnaire> questionnaireModel = questionnaireService.findById(id);
		ResponseEntity<QuestionnaireDTO> respEnt = respEntOK;
		if (questionnaireModel.isPresent()) {
			questionnaireService.delete(questionnaireMapper.dtoToModel(dto));
			log.info("Succesfully delete questionnaire " + id + " and answers linked to questionnaire");
		} else
			respEnt = respEntNotFound;
		return respEnt;

	}
}
