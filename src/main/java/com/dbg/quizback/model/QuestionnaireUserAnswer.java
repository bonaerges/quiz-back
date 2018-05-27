package com.dbg.quizback.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionnaireUserAnswer {

//	 @EmbeddedId 
//	public QuestionUserAnswerId id;
	 private Question question;
	 private Answer selectedAnswer;
	 private User user;
	
	 
	 

}
