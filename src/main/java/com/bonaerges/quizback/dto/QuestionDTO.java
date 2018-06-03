package com.bonaerges.quizback.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class QuestionDTO {

	private String description;
	
	private List<AnswerDTO> answer;


}
