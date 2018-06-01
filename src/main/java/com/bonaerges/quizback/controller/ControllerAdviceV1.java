package com.bonaerges.quizback.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bonaerges.quizback.dto.ErrorDTO;
import com.bonaerges.quizback.exception.DuplicatedException;
import com.bonaerges.quizback.exception.NotFoundException;

@ControllerAdvice(basePackages="com.bonaerges.quizback.controller.v1")
public class ControllerAdviceV1 {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO error(NotFoundException nfe) {
		return new ErrorDTO(nfe.getMessage());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorDTO error(DuplicatedException nfe) {
		return new ErrorDTO(nfe.getMessage());
	}

	
}
