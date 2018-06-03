package com.bonaerges.quizback.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.questionnaire.QuestionnaireMapper;
import com.bonaerges.quizback.component.mapper.questionnaireUserAnswer.QuestionnaireUserAnswerMapper;
import com.bonaerges.quizback.dto.QuestionnaireQADTO;
import com.bonaerges.quizback.dto.QuestionnaireUserAnswerDTO;
import com.bonaerges.quizback.model.QuestionUserAnswerPK;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
import com.bonaerges.quizback.service.QuestionnaireUserAnswerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/quiz")
public class QuestionnaireUserAnswerController {

	@Autowired
	QuestionnaireUserAnswerService questionnaireUserAnswerService;

	@Autowired
	QuestionnaireUserAnswerMapper questionnaireUserAnswerMapper;

	@Autowired
	QuestionnaireMapper questionMapper;

	final ResponseEntity<QuestionnaireQADTO> respEntOK = new ResponseEntity<QuestionnaireQADTO>(HttpStatus.OK);
	final ResponseEntity<QuestionnaireQADTO> respEntNotFound = new ResponseEntity<QuestionnaireQADTO>(
			HttpStatus.NOT_FOUND);

	/************************************* HTTP METHOD GET ************************************/
	
	// questionnaire?page=X&size=X
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<QuestionnaireQADTO> findAll(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
		List<QuestionnaireUserAnswer> questionnaires = questionnaireUserAnswerService
				.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
		log.info("findAll questionnaires count is: " + Integer.toString(questionnaires.size()));
		return questionnaireUserAnswerMapper.modelToDto(questionnaires);
	}

	@ResponseBody
	@RequestMapping(value = "/{idQ}/", method = RequestMethod.GET)
	public List<QuestionnaireUserAnswer> findAllByQuestionnaire(@PathVariable("idQ") Integer idQuestionnaire) {

	//	QuestionUserAnswerPK pkID = new QuestionUserAnswerPK();
		List<QuestionnaireUserAnswer> questionnaires = questionnaireUserAnswerService.findByIdQuestionnaire(idQuestionnaire);
		// log.info("findAll questionnaires count is: " +
		// Integer.toString(questionnaires.size()));
		return questionnaires;
	}

	/************************************* HTTP METHOD POST*************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public QuestionnaireUserAnswer create(@RequestBody QuestionnaireUserAnswer idDTO) {
		final QuestionnaireUserAnswer createQuestionnaireUA = questionnaireUserAnswerService.create(idDTO);
		log.info("Questionnaire User Answer succesfuly created.");
		return createQuestionnaireUA;
	}

	/************************************* HTTP METHOD PUT*************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	// ONLY WILL BE ALOwED TO CHANGE ANSWER SELECTED BY USER ANYMORE
	ResponseEntity<QuestionnaireQADTO> update(@RequestBody QuestionnaireUserAnswerDTO idDTO) {

		if (idDTO.getIdQuestionnaire() != null) {
			List<QuestionnaireUserAnswer> questionnaires = questionnaireUserAnswerService.findByIdQuestionnaire(idDTO.getIdQuestionnaire());
			QuestionUserAnswerPK pk = new QuestionUserAnswerPK();
			pk.setAnswerId(idDTO.getIdAnswer());
			//pk.setQuestionId(idDTO.getIdQuestion());
			//pk.setQuestionnaireId(idDTO.getIdQuestionnaire());
			//pk.setUserId(idDTO.getIdUser());
			
			questionnaireUserAnswerService.findById(pk);
		}
		ResponseEntity<QuestionnaireQADTO> respEnt = new ResponseEntity<QuestionnaireQADTO>(HttpStatus.NOT_FOUND);
		// if (questionnaireUAModel.isPresent()) {
		// // ONly is allowed to change response of user anymore
		// questionnaireUAModel.get().setIdAnswer(idDTO.getIdAnswer());
		// questionnaireUAModel.get().setDate(new Date());
		// questionnaireUserAnswerService.update(questionnaireUAModel.get());
		// respEnt = new ResponseEntity<QuestionnaireQADTO>(HttpStatus.OK);
		// } else
		// respEnt = new ResponseEntity<QuestionnaireQADTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	/************************************* HTTP METHOD DELETE*************************************/
	 @ResponseBody
	 @RequestMapping(method = RequestMethod.DELETE)
	 void delete(@RequestBody QuestionnaireUserAnswer idDTO) {
		 	questionnaireUserAnswerService.delete(idDTO);
			log.info("Questionnaire User Answer succesfuly created.");
	 }
}
