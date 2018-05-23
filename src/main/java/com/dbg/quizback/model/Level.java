package com.dbg.quizback.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Level {

	public static final String FIELD_ID = "idLevel";
	public static final String FIELD_ID_FK_LEVEL="idLevelQ";
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String name;

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;

	 //FOREIGN KEYS
	 
	@OneToOne(fetch = FetchType.LAZY, mappedBy = Question.LEVEL_FIELD)
	private Question question;

	@Override
	public String toString() {
		return "Level id= " + id + " , name="+ name ;
	}
}
