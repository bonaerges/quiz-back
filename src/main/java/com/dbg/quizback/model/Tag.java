package com.dbg.quizback.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
public class Tag {

	public static final String FIELD = "tag";
	public static final String FIELD_ID = "idTag";
	public static final String FIELD_ID_FK_TAG = "idTagQ";
	
	//TABLE FIELDS
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String name;

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	
	//FOREIGN KEY
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = Questionnaire.TAG_FIELD)
	private List<Questionnaire> questionnaire;
	
	@Override
	public String toString() {
		return "Tag id= " +id + " , name= "+ name ;
	}
}
