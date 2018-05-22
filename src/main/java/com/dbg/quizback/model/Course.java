package com.dbg.quizback.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
public class Course {

	public static final String FIELD_ID = "";
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String description;
	
	 @Column
	 private Date createdOn;
	 
	 @Column
	 private String createdBy;
	 
	    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Questionnaire.COURSE_FIELD)
	private List<Questionnaire> questionnaire;
	
	@Override
	public String toString() {
		return "Course id= " +id + " , description= "+ description ;
	}
}
