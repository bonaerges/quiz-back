package com.bonaerges.quizback.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDTO {

	private String message;
	private HttpStatus httpStatusCode;

	public ErrorDTO() { }

	public ErrorDTO(final String message) {
		this.message = message;
	}
	public ErrorDTO(final HttpStatus HTTPStatusCode,final String message) {
		this.httpStatusCode=HTTPStatusCode;
		this.message = message;
	}
}
