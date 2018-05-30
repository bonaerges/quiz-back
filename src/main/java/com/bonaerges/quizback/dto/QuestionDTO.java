package com.bonaerges.quizback.dto;

import java.util.List;
import java.util.Set;

import com.bonaerges.quizback.model.Level;

import lombok.Data;

@Data
public class QuestionDTO {

	private String description;
	
	private List<AnswerDTO> answer;


}
