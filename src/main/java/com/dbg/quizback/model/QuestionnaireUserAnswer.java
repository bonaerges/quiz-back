package com.dbg.quizback.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class QuestionnaireUserAnswer {

	 @EmbeddedId 
	public QuestionUserAnswerId id;

}
