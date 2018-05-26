package com.dbg.quizback.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class QuestionDTO {

	private String description;
	
	private List<AnswerDTO> answer;
	
	private LevelDTO level;
	
	private Set<TagDTO> tag;
}
