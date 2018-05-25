package com.dbg.quizback.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CourseDTO {

	private String description;
	
	private Date startDate;
	 
	private Date finishDate;
	 
	private Date createdOn;
}
