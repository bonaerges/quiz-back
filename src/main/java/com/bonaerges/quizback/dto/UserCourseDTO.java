package com.bonaerges.quizback.dto;

import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserCourseDTO {
	
	private CourseDTO course;
	
	private Set<UserDTO> users;
}
