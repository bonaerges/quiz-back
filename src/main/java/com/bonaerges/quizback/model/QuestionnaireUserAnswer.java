package com.bonaerges.quizback.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class QuestionnaireUserAnswer {

	public static final String FIELD_ID = "questionUserAnswerId";
	public static final String FIELD = "questionnaireUserAnswer";
	public static final String TABLE_QA_NAME="questionnaireUserAnswer";
	
	@EmbeddedId 
	@Column(name=FIELD_ID)
	 public QuestionUserAnswerId id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	 
	//FOREIGN KEY
	//QUESTIONNAIREUSERANSWER (N) <--> (M) RESULT
	@ManyToMany(mappedBy = FIELD, fetch = FetchType.LAZY)
	private List<Result> result;
	

}
