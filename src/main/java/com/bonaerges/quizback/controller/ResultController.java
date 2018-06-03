package com.bonaerges.quizback.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.answer.AnswerMapper;
import com.bonaerges.quizback.component.mapper.question.QuestionViewDTOMapper;
import com.bonaerges.quizback.component.mapper.questionnaireUserAnswer.QuestionnaireUserAnswerMapper;
import com.bonaerges.quizback.dto.QuestionnaireFilledDTO;
import com.bonaerges.quizback.dto.QuestionnaireQADTO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;
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
	QuestionViewDTOMapper questionViewMapper;
	
	@Autowired
	AnswerMapper answerMapper;
	
	final ResponseEntity<List<QuestionnaireFilledDTO>> respEntOK=new ResponseEntity<>(HttpStatus.OK);
	final ResponseEntity<List<QuestionnaireFilledDTO>> respEntNotFound=new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	/************************************
	 * HTTP METHOD GET
	 * @throws NotFoundException 
	 *************************************/
	// result BY questionnaire ID based on a course
	@ResponseBody
	@RequestMapping(value = "/{idCourse}", method = RequestMethod.GET)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<List<QuestionnaireFilledDTO>> findById(@PathVariable("idCourse") Integer idCourse) throws NotFoundException {
		Optional<Course> course = resultService.getCourse(idCourse);
		ResponseEntity<List<QuestionnaireFilledDTO>> respEnt=respEntOK;
		List<QuestionnaireFilledDTO> listResult=new ArrayList<QuestionnaireFilledDTO>();
	
		if (course.isPresent()) {
			List<Questionnaire> quiz=course.get().getQuestionnaire();
			quiz.forEach( quizQ ->{
				QuestionnaireFilledDTO qFilled;			
				//For each questionnaire get the result of course/Questionnaire
				resultService.getQuestionnaire(quizQ.getId());
				//THIS SHOUlD TO BE DONE IN MAPPER TRY TO DO LATER
				qFilled= new QuestionnaireFilledDTO();
				//DTO questionnaireDescription
				qFilled.setQuestionnaireDescription(quizQ.getDescription());
				
				//DTO courseDescription
				qFilled.setCourseDescription(quizQ.getCourse().getDescription());
				
				//DTO private List<QuestionDTO> question - anSwer;
				Optional<Questionnaire> questionsQuiz=quizQ.getCourse().getQuestionnaire().stream().filter( q -> q.getId() == quizQ.getId()).findFirst();
				qFilled.setQuestion(questionViewMapper.modelToDto(questionsQuiz.get().getQuestion()));
			});
			
			
			//DTO AnswerDTO correctAnswer
			
		//	Answer answer=questionsQuiz.get().getQuestion().forEach(q -> q.getAnswer());
			//Answer answerCorrect = questionsQuiz.stream().filter(q -> q.getCorrectAnswer()).get();
			//qFilled.setCorrectAnswer(correctAnswer);
			//qFilled.getAnswerIdSelected(answerMapper.modelToDto(answerCorrect));
			//			List<Result> results= resultService.findAllByQuestionnarie(idQuestionnaire);
			
		}
		return respEnt;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{idQuestionnaire}/user/{idUser}", method = RequestMethod.GET)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<QuestionnaireQADTO> findByUserandQuiz(
			@PathVariable("idQuestionnaire") Integer idQuestionnaire,
			@PathVariable("idUser") Integer idUser) throws NotFoundException {
		ResponseEntity<QuestionnaireQADTO> respEnt=new ResponseEntity<QuestionnaireQADTO>(HttpStatus.OK);
		List<QuestionnaireUserAnswer> resultUA= resultService.findUsersAnswerByQuestionnarie(idQuestionnaire, idUser);
		List<QuestionnaireQADTO> answerResult=questionnaireUserAnswerMapper.modelToDto(resultUA);
		
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
