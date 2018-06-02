package com.bonaerges.quizback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionnaireFilledDTO  extends QuestionnaireQADTO {

	private AnswerDTO correctAnswer;
	
}
