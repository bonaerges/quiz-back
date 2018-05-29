package com.dbg.quizback.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionnaireQADTO {

	private String description;
	
	private String courseDescription;
	
	private List<QuestionDTO> question;
}
