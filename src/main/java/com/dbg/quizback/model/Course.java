package com.dbg.quizback.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Course {

	public static final String FIELD_ID = "idCourse";
	public static final String FIELD_COURSE = "course";
	public static final String FIELD_USER = "user";
	public static final String TABLE_COURSE_USER = "courseUser";
	
	//TABLE FIELDS
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false, unique=true)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	 
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishDate;
	 
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	 
	@Column(nullable = true)
	private String createdBy; 
	 
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date update;
	 
	//FOREIGN KEYS
	//COURSE (1) --> (M) QUESTIONNAIRE
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Questionnaire.COURSE_FIELD)
	private Set<Questionnaire> questionnaire;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name=TABLE_COURSE_USER, 
	joinColumns=@JoinColumn(name=FIELD_ID, referencedColumnName=FIELD_ID),
	inverseJoinColumns=@JoinColumn(name=User.FIELD_ID, referencedColumnName=User.FIELD_ID))
	private Set<User> user;
	
//
	
	@Override
	public String toString() {
		return "Course id= " +id + " , description= "+ description ;
	}
}
