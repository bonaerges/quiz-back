package com.dbg.quizback.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CourseDTO {

	private String description;
	
	private Date startDate;
	 
	private Date finishDate;
	 
	private Date createdOn;
	
	private List<UserDTO> users;
}
