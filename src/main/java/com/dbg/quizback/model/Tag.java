package com.dbg.quizback.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Tag {

	public static final String FIELD = "tag";
	public static final String FIELD_ID = "idTag";
	public static final String FIELD_ID_FK_TAG = "idTagQ";
	
	//TABLE FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String name;

//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	
	//FOREIGN KEY
	//QUESTION (N) <--> (M) TAG
	@ManyToMany(fetch = FetchType.LAZY,mappedBy=FIELD)
	private Set<Question> question;
	
	@Override
	public String toString() {
		return "Tag id= " +id + " , name= "+ name ;
	}
}
