package com.dbg.quizback.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Questionnaire {

	public static final String FIELD_ID = "idQuestionnaire";
	//public static final String FIELD_ID_FK_TAG = "idQuestionnaireTag";
	//public static final String FIELD_ID_FK_QUESTION = "idQuestionnaireQuestion";
	//public static final String FIELD_ID_FK_COURSE="idQuestionnaireCourse";
	//public static final String FIELD_ID_FK_RESULT = "idQuestionnaireResult";
	public static final String TAG_FIELD = "tag";
	public static final String QUESTION_FIELD = "question";
	public static final String COURSE_FIELD = "course";
	public static final String TABLE_QUESTIONNAIRE_TAG = "questionnaireTag";
	public static final String TABLE_QUESTIONNAIRE_QUESTION = "questionnaireQuestion";
	
	//TABLE FIELDS
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false,unique=true)
	private String description;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
      name=TABLE_QUESTIONNAIRE_QUESTION,
      joinColumns=@JoinColumn(name=FIELD_ID, referencedColumnName=FIELD_ID),
      inverseJoinColumns=@JoinColumn(name=Question.FIELD_ID, referencedColumnName=Question.FIELD_ID))
	private List<Question> question;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = COURSE_FIELD)
	private Course course;
	
	@Override
	public String toString() {
		return "Questionnaire id= " + id + " , description= "+ description ;
	}
}
