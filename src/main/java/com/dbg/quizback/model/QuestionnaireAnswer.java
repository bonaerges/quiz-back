package com.dbg.quizback.model;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

public class QuestionnaireAnswer {

	//public static final String TABLE_NAME_QUESTION_TAG = "QuestionTag";
	
//	private Course course;
//	
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinTable(name=TABLE_COURSE_USER, 
//	joinColumns=@JoinColumn(name=FIELD_ID, referencedColumnName=FIELD_ID),
//	inverseJoinColumns=@JoinColumn(name=User.FIELD_ID, referencedColumnName=User.FIELD_ID))
//	private User user;
//	
//	private Questionnaire questionnaire;
//	
//	private Answer anwser;
}
