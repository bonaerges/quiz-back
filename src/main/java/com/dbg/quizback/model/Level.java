package com.dbg.quizback.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
public class Level {

	public static final String FIELD_ID = "idLevel";
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String name;


	@OneToOne(fetch = FetchType.LAZY, mappedBy = Question.LEVEL_FIELD)
	private Question question;

	@Override
	public String toString() {
		return "Level id= " + id + " , name="+ name ;
	}
}
