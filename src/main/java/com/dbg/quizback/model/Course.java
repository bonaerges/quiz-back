package com.dbg.quizback.model;

import java.util.Date;
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
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String description;
	
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date startDate;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date finishDate;
	 
	 @Column
	 private Date createdOn;
	 
	 @Column
	 private String createdBy;
	 
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Questionnaire.FIELD_ID_FK_COURSE)
	private List<Questionnaire> questionnaire;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name=TABLE_COURSE_USER, 
	joinColumns=@JoinColumn(name=FIELD_ID, referencedColumnName=FIELD_ID),
	inverseJoinColumns=@JoinColumn(name=User.FIELD_ID, referencedColumnName=User.FIELD_ID))
	private List<User> user;
	
	
	@JoinColumn(name = FIELD_COURSE)
	@ManyToOne(fetch = FetchType.LAZY)
	private Course course;
	
	@Override
	public String toString() {
		return "Course id= " +id + " , description= "+ description ;
	}
}
