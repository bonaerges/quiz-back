package com.bonaerges.quizback.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	public static final String FIELD_ID_PK = "idResult";

	//TABLE FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_ID_PK)
	private Integer id;
		
	private double averageNote=0;
	
	private int totalAnswerOK=0;
	
	private int totalAnswerKO=0;
			
	private  int totalQuestions=0;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	
	//FOREIGN KEYS
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Questionnaire.FIELD_ID)
	private Questionnaire questionary;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = FIELD_USER)
	private User user;
	
	//RESULT (N) <-- (1) QUESTIONNAIREUSERANSWER
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = QuestionnaireUserAnswer.TABLE_QA_NAME,
			joinColumns = {
					@JoinColumn(name = FIELD_ID_PK, nullable = false, updatable = false)
			}, 
			inverseJoinColumns = { 
				@JoinColumn(name = QuestionUserAnswerPK.FIELD_ID_ANSWER, nullable = false, updatable = false),
				@JoinColumn(name = QuestionUserAnswerPK.FIELD_ID_QUESTION, nullable = false, updatable = false),
				@JoinColumn(name = QuestionUserAnswerPK.FIELD_ID_QUESTIONNAIRE, nullable = false, updatable = false),
				@JoinColumn(name = QuestionUserAnswerPK.FIELD_ID_USER, nullable = false, updatable = false) 
			})
	private List<QuestionnaireUserAnswer> questionnaireUserAnswer;
}
