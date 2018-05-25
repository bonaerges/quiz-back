package com.dbg.quizback.dto;

import lombok.Data;

@Data
public class ErrorDTO {

	private String message;

	public ErrorDTO() { }

	public ErrorDTO(final String message) {
		this.message = message;
	}
}
