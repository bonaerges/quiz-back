package com.bonaerges.quizback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionnaireQADTO {

	private String questionnaireDescription;
	
	private String courseDescription;
	
	private QuestionDTO question;
	
	private AnswerDTO selectAnswer;
	
	
}
