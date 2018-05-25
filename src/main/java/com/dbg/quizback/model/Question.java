package com.dbg.quizback.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	public static final String TABLE_NAME_QUESTION_TAG = "QuestionTag";
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String description;

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	
	 //FOREIGN KEYS
	 
	//QUESTION (1) --> (M) ANSWER
	@OneToMany(fetch = FetchType.LAZY, mappedBy = QUESTION_FIELD)
	private List<Answer> answer;
	
	//QUESTION (N) <-- (1) LEVEL
	@JoinColumn(name = Level.FIELD_ID)
	@ManyToOne(fetch = FetchType.LAZY)
	private Level level;
	
	//QUESTION (N) <--> (M) TAG
	@ManyToMany
	@JoinTable(name=TABLE_NAME_QUESTION_TAG,
     joinColumns=@JoinColumn(name=FIELD_ID, referencedColumnName=FIELD_ID),
     inverseJoinColumns=@JoinColumn(name=Tag.FIELD_ID, referencedColumnName=Tag.FIELD_ID))
	private Set<Tag> tag;

	@Override
	public String toString() {
		return "Question id= " + id + " , description= "+ description;
	}

	
}
