package com.dbg.quizback.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
public class Tag {

	public static final String FIELD = "tag";
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = Questionnaire.TAG_FIELD)
	private List<Questionnaire> questionnaire;
	
	@Override
	public String toString() {
		return "Tag id= " +id + " , name= "+ name ;
	}
}
