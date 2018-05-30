package com.bonaerges.quizback.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CoursePostDTO extends CourseDTO {
	
	private List<UserDTO> users;
}
