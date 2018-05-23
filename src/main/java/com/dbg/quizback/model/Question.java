package com.dbg.quizback.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Question {

	public static final String FIELD_ID = "idQuestion";
	public static final String FIELD_ID_FK_LEVEL = "idQuestionLevel";
	public static final String LEVEL_FIELD = "level";
	public static final String QUESTION_FIELD = "question";
	
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date update;
	
	 //FOREIGN KEYS
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = QUESTION_FIELD)
	private List<Answer> answer;
	
	@JoinColumn(name = Level.FIELD_ID)
	@ManyToOne(fetch = FetchType.LAZY)
	private Level level;
	
	
	@JoinColumn(name = Tag.FIELD_ID)
	@ManyToOne(fetch = FetchType.LAZY)
	private Tag tag;

	@Override
	public String toString() {
		return "Question id= " + id + " , description= "+ description;
	}

	
}
