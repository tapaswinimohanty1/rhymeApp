/**
 * 
 */
package com.sc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author tapaswini
 *
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ParseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4568076212407650933L;

	public ParseException() {
		super();
	}
}
