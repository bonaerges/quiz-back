package com.bonaerges.quizback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionnaireUserAnswerDTO {
	
	
	private Integer idQuestion;
	
	private Integer idUser;
	
	private Integer idAnswer;
	
	private Integer idQuestionnaire;

}
