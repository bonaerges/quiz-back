package com.dbg.quizback.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class QuestionnaireUserAnswer {

	 public static final String FIELD_ID = "questionUserAnswerId";

	@EmbeddedId 
	@Column(name=FIELD_ID)
	 public QuestionUserAnswerId id;
	 
	 @MapsId(QuestionUserAnswerId.FIELD_ID_QUESTION) // maps questionId attribute of embedded id
	 @OneToOne
	 @JoinColumn(name=Question.FIELD_ID, insertable = false, updatable = false)
	 private Question question;
	 
	 @MapsId(QuestionUserAnswerId.FIELD_ID_ANSWER) // maps answer attribute of embedded id
	 @OneToOne
	 @JoinColumn(name=Answer.FIELD_ID, insertable = false, updatable = false)
	 private Answer answer;

	 @MapsId(QuestionUserAnswerId.FIELD_ID_USER) // maps user attribute of embedded id
	 @OneToOne
	 @JoinColumn(name=User.FIELD_ID, insertable = false, updatable = false)
	 private User user;

	 
	 @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = Questionnaire.FIELD_ID)
		private Questionnaire questionary;

	

}
