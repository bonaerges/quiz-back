package com.bonaerges.quizback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResultQuizPostDTO {

	private Integer questionnaireId;
	
	private Integer courseId;
	
}
