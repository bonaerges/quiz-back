package com.bonaerges.quizback.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseDTO  {

	private String description;
	
	private Date startDate;
	 
	private Date finishDate;
	 
	private Date createdOn;
}
