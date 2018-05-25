package com.dbg.quizback.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class QuestionnaireUserAnswer {

	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name=Questionnaire.FIELD_ID)
	private Questionnaire questionnaire;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name=User.FIELD_ID)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name=Question.FIELD_ID)
	private Question question;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name=Answer.FIELD_ID)
	private Answer answer;
}
