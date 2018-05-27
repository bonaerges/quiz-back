package com.dbg.quizback.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionnaireDTO {

	private String description;
	
	private CourseDTO course;
	
	private List<QuestionDTO> question;
}
