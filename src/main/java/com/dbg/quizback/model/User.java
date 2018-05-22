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
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {

	public static final String FIELD_ID = "idUser";
	public static final String FIELD_ID_FK_COURSE="idUserCourse";
	
	@Id
	@GeneratedValue
	@Column(name=FIELD_ID)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;
	
	private float averageNote;
	
	@Column(nullable = false)
	private String role;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = Result.FIELD_USER)
	private List<Result> result;

	@ManyToMany(fetch=FetchType.LAZY, mappedBy = Course.FIELD_USER)
	private List<Course> course;
	
	@Override
	public String toString() {
		return "User id= " + this.id + " , name= "+ name + ", Average Note= "+ averageNote;
	}

}


