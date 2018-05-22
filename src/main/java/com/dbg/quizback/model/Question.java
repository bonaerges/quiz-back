package com.dbg.quizback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Question {

	public static final String FIELD_ID = "idQuestion";
	public static final String FIELD_ID_FK_LEVEL = "idQuestionL";
	public static final String FIELD_ID_FK_ANSWER = "idQuestionA";
	public static final String ANSWER_FIELD = "answer";
	public static final String LEVEL_FIELD = "level";
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String description;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name=FIELD_ID_FK_ANSWER)
	private Answer answer;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name=FIELD_ID_FK_LEVEL)
	private Level level;
	
	@Override
	public String toString() {
		return "Question id= " + id + " , description= "+ description;
	}

	
}
