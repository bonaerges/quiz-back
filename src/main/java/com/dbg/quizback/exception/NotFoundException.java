package com.dbg.quizback.exception;

public class NotFoundException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8212600186890901351L;
	public static final String MSG="NOT FOUND";
	
	public NotFoundException() {
		super(MSG);
	}
	public NotFoundException(String msg) {
		super(msg);
	}
	
}
