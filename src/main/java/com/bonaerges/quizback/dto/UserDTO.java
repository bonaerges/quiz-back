package com.bonaerges.quizback.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {

	//private Integer id;
	private String email;
	private String name;
	private String surname;
}
