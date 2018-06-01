package com.bonaerges.quizback.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserCourseDTO {
	
	private CourseDTO course;
	
	private List<UserDTO> users;
}
