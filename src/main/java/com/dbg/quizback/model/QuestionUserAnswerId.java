package com.dbg.quizback.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class QuestionUserAnswerId  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6629755509551390087L;

	public static final String FIELD_ID_ANSWER= "idAnswer";
	
	public static final String FIELD_ID_QUESTION= "idQuestion";
	
	public static final String FIELD_ID_QUESTIONNAIRE= "idQuestionnaire";
	
	public static final String FIELD_ID_USER= "idUser";
	
	private Integer idQuestionnaire;
	
	private Integer idUser;

	private Integer idQuestion;
	
	private Integer idAnswer;
	
	  public boolean equals(Object object) {
	        if (object instanceof QuestionUserAnswerId) {
	        	QuestionUserAnswerId pk = (QuestionUserAnswerId)object;
	        	return pk.idQuestionnaire == idQuestionnaire && pk.idUser == idUser && pk.idQuestion == idQuestion && pk.idAnswer==idAnswer;
	        } else {
	            return false;
	        }
	    }

	    public int hashCode() {
	        return Objects.hash(idQuestionnaire,idUser,idQuestion,idAnswer);
	    }
}

