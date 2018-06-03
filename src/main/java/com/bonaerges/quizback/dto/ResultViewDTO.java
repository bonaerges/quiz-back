package com.bonaerges.quizback.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResultViewDTO {

	private String name;
	
	private Date date;
	
	private double averageNote=0;
	
	private int totalAnswerOK=0;
	
	private int totalAnswerKO=0;
			
	private  int totalQuestions=0;
	
	private UserDTO user;
	
}
