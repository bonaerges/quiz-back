package com.bonaerges.quizback.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	public static final String QUESTIONNAIRE_FIELD = "questionnaire";
	//TABLE FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false,unique=true)
	private String description;
	
	@OneToMany(mappedBy = QUESTIONNAIRE_FIELD, fetch = FetchType.LAZY)
	private List<Question> question;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = COURSE_FIELD,nullable=false)
	private Course course;
	
	@Override
	public String toString() {
		return "Questionnaire id= " + id + " , description= "+ description ;
	}
}
