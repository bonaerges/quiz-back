package com.bonaerges.quizback.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDTO {

	private String description;
	
	private List<AnswerDTO> answer;


}
