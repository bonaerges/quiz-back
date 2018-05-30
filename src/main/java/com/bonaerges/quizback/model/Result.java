package com.bonaerges.quizback.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Result {

	public static final String FIELD_USER = "user";
	public static final String FIELD_ID = "idUser";
	public static final String FIELD_ID_FK = "idResult";

	//TABLE FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_ID)
	private Integer id;
		
	private double averageNote=0;
	
	private int totalAnswerOK=0;
	
	private int totalAnswerKO=0;
			
	private  int totalQuestions=0;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	
	//FOREIGN KEYS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Questionnaire.FIELD_ID)
	private Questionnaire questionary;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = FIELD_USER)
	private User user;
}
