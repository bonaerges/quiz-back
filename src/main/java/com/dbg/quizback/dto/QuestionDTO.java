package com.dbg.quizback.dto;

import java.util.List;

import com.dbg.quizback.model.Answer;

import lombok.Data;

@Data
public class QuestionDTO {

	private String description;
	
	private List<Answer> answer;
	
}
