package com.dbg.quizback.exception;

public class DuplicatedException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8212600186890901351L;
	public static final String MSG="DUPLICATED VALUE FOUND";
	
	public DuplicatedException() {
		super(MSG);
	}
	
}
