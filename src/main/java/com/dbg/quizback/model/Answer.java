package com.dbg.quizback.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Answer {

	public static final String FIELD= "question";
	public static final String FIELD_ID= "idAnswer";
	public static final String ANSWER_FIELD = "answer";
	//public static final String FIELD_ID_FK_ANSWER = "idAnswerQuestion";
	
	//TABLE FIELDS
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String description;
	
	private Boolean isCorrect;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	
	 //FOREIGN KEYS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = FIELD)
	private Question question;
	
	@Override
	public String toString() {
		return "Answer id= " + id + " , description= "+ description  + ", Correct answer= "+ isCorrect;
	}
}
