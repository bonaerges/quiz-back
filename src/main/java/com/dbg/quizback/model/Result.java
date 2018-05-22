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

@Getter
@Setter
@Entity
public class Result {

	public static final String FIELD_USER = "user";
	public static final String FIELD_ID = "idUser";
	public static final String FIELD_ID_FK = "idResult";

	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@JoinColumn(name = Questionnaire.FIELD_ID_FK_RESULT)
	@ManyToOne(fetch = FetchType.LAZY)
	private Questionnaire questionary;

	@JoinColumn(name = FIELD_ID_FK)
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
