package com.dbg.quizback.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Result {

	public static final String FIELD_USER = "user";

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@JoinColumn(name = "idResult", insertable=false, updatable=false)
	@ManyToOne
	private User user;

}
