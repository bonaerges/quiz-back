package com.bonaerges.quizback.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class QuestionUserAnswerPK  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6629755509551390087L;

	public static final String FIELD_ID_ANSWER= "answerId";
	
	public static final String FIELD_ID_QUESTION= "questionId";
	
	public static final String FIELD_ID_QUESTIONNAIRE= "questionnaireId";
	
	public static final String FIELD_ID_USER= "userId";
	
	 @Column(name = FIELD_ID_QUESTIONNAIRE, nullable = false)
	 
	private Integer questionnaireId;
	
	 @Column(name = FIELD_ID_USER, nullable = false)
	private Integer userId;

	 @Column(name = FIELD_ID_QUESTION, nullable = false)
	private Integer questionId;
	
	 @Column(name = FIELD_ID_ANSWER, nullable = false)
	private Integer answerId;
	
	  public boolean equals(Object object) {
	        if (object instanceof QuestionUserAnswerPK) {
	        	QuestionUserAnswerPK pk = (QuestionUserAnswerPK)object;
	        	return  pk.questionnaireId == questionnaireId && pk.userId == userId && pk.questionId == questionId && pk.answerId==answerId;
	        } else {
	            return false;
	        }
	    }

	    public int hashCode() {
	        return Objects.hash(questionnaireId,userId,questionId,answerId);
	    }
}

