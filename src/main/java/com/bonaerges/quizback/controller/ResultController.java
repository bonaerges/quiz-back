package com.bonaerges.quizback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.questionnaireUserAnswer.QuestionnaireUserAnswerMapper;
import com.bonaerges.quizback.service.ResultService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/result")
public class ResultController {

	@Autowired
	ResultService resultService;
	

	
	@Autowired
	QuestionnaireUserAnswerMapper questionnaireUserAnswerMapper;
	
	/************************************HTTP METHOD GET *************************************/
	//result BY questionnaire ID based on a course
//	@ResponseBody
//	@RequestMapping(value="/{id}",method=RequestMethod.GET)
//	public ResponseEntity<LiestionnaireQADTO> findById(@RequestBody ResultQuizPostDTO resultQuizDTO)
//	{
//		Optional<Course> courseQuiz=courseService.findById(resultQuizDTO.getCourseId());
//		ResponseEntity<QuestionDTO> respEnt;
//		if (courseQuiz.isPresent()) {
//			Optional<Questionnaire> quest=courseQuiz.get().getQuestionnaire().stream().filter(quesTCourse -> quesTCourse.getId() ==resultQuizDTO.getQuestionnaireId()).findFirst();
//			QuestionnaireQADTO quizQ= new QuestionnaireQADTO();
//			List<QuestionnaireQADTO> resultList=questionnaireUserAnswerMapper.modelToDto(quest.get().getQuestionnaireUserAnswer());
//			log.info("Question " + id + " found");
//			respEnt=new ResponseEntity<QuestionDTO>(questionMapper.modelToDto(questionModel.get()),HttpStatus.OK);
//		}
//		
//	}
	
	/************************************HTTP METHOD POST *************************************/
	
	/************************************HTTP METHOD PUT *************************************/
	
	/************************************HTTP METHOD DELETE *************************************/
}
