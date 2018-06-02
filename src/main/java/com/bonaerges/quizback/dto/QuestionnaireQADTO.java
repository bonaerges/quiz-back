package com.bonaerges.quizback.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionnaireQADTO {

	private String questionnaireDescription;
	
	private String courseDescription;
	
	private List<QuestionDTO> question;
	
	private Integer answerIdSelected;
	
	
}
