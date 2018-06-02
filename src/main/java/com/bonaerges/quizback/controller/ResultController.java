package com.bonaerges.quizback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.question.QuestionMapper;
import com.bonaerges.quizback.component.mapper.questionnaireUserAnswer.QuestionnaireUserAnswerMapper;
import com.bonaerges.quizback.dto.QuestionnaireFilledDTO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.Result;
import com.bonaerges.quizback.service.ResultService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/result")
public class ResultController {

	@Autowired
	ResultService resultService;

	@Autowired
	QuestionnaireUserAnswerMapper questionnaireUserAnswerMapper;

	@Autowired
	QuestionMapper questionMapper;
	
	final ResponseEntity<QuestionnaireFilledDTO> respEntOK=new ResponseEntity<QuestionnaireFilledDTO>(HttpStatus.OK);
	final ResponseEntity<QuestionnaireFilledDTO> respEntNotFound=new ResponseEntity<QuestionnaireFilledDTO>(HttpStatus.NOT_FOUND);
	
	/************************************
	 * HTTP METHOD GET
	 * @throws NotFoundException 
	 *************************************/
	// result BY questionnaire ID based on a course
	@ResponseBody
	@RequestMapping(value = "/{idQuestionnaire}", method = RequestMethod.GET)
	public ResponseEntity<QuestionnaireFilledDTO> findById(@PathVariable("idQuestionnaire") Integer idQuestionnaire) throws NotFoundException {
		Optional<Questionnaire> quiz = resultService.getQuestionnaire(idQuestionnaire);
		ResponseEntity<QuestionnaireFilledDTO> respEnt=respEntOK;
		if (quiz.isPresent()) {
			quiz.get().getCourse();
			List<Result> results= resultService.findAllByQuestionnarie(idQuestionnaire);
		}
		return respEnt;
	}

//	@ResponseBody
//	@RequestMapping( method = RequestMethod.GET)
//	public ResponseEntity<QuestionnaireFilledDTO> findAll(@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
//			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
//	//	Optional<Course> courseQuiz = resultService.getCourse(resultQuizDTO.getCourseId());
//		ResponseEntity<QuestionnaireFilledDTO> respEnt=respEntOK;
//		List<Result> results= resultService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
//		QuestionnaireFilledDTO qFilled;
//	
//		results.forEach(r -> {
//			qFilled = new QuestionnaireFilledDTO();
//			Questionnaire quiz=r.getQuestionary()
//			qFilled.setQuestion(questionMapper.modelToDto(quiz.getQuestion()));
//			qFilled.setQuestionnaireDescription(quiz.getDescription());
//			Course c= quiz.getCourse();
//			qFilled.setCourseDescription(c.getDescription());
//			final Integer idQuestionnaire= 
//	
//		});
//	//	if (courseQuiz.isPresent()) {
////			Optional<Questionnaire> quest = courseQuiz.get().getQuestionnaire().stream()
////					.filter(quesTCourse -> quesTCourse.getId() == resultQuizDTO.getQuestionnaireId()).findFirst();
////			resultService.findById(id)
////			QuestionnaireQADTO quizQ = new QuestionnaireQADTO();
////			List<QuestionnaireQADTO> resultList = questionnaireUserAnswerMapper
////					.modelToDto(quest.get().getQuestionnaireUserAnswer());
//		//	log.info("Question " + id + " found");
//			//respEnt = new ResponseEntity<QuestionDTO>(questionMapper.modelToDto(questionModel.get()), HttpStatus.OK);
//		}
//		return respEnt;
//	}

	/************************************
	 * HTTP METHOD POST
	 *************************************/

	/************************************
	 * HTTP METHOD PUT
	 *************************************/

	/************************************
	 * HTTP METHOD DELETE
	 *************************************/
}
