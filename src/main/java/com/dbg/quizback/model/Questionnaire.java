package com.dbg.quizback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Questionnaire {

	public static final String FIELD_ID_FK_COURSE = "idQuestionnaireC";
	public static final String FIELD_ID_FK_TAG = "idQuestionnaireT";
	public static final String TAG_FIELD = "tag";
	public static final String COURSE_FIELD = "course";
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String description;
	
	@JoinColumn(name = FIELD_ID_FK_COURSE, insertable=false, updatable=false)
	@ManyToOne
	private Course course;
	
	@JoinColumn(name = FIELD_ID_FK_TAG, insertable=false, updatable=false)
	@ManyToOne
	private Tag tag;
	
	@Override
	public String toString() {
		return "Questionnaire id= " + id + " , description= "+ description ;
	}
}
