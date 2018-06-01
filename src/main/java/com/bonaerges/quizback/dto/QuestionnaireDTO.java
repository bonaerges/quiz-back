package com.bonaerges.quizback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionnaireDTO {

	private String description;
	
	private Integer idCourse;
	
	//private List<QuestionDTO> question;
}
