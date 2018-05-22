package com.dbg.quizback.model;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
public class Answer {

	public static final String FIELD= "question";
	public static final String FIELD_ID= "idQuestion";
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String description;
	
	private Boolean isCorrect;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Question.ANSWER_FIELD)
	private List<Question> questions=new ArrayList<>();
	
	@Override
	public String toString() {
		return "Answer id= " + id + " , description= "+ description  + ", Correct answer= "+ isCorrect;
	}
}
