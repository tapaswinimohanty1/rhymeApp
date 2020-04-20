/**
 * 
 */
package com.sc.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sc.dto.RhymeData;
import com.sc.exception.TransactionException;

/**
 * @author tapaswini
 *
 */
@Component
public class RhymeDataValidator {

	public void validate(RhymeData trn) {
		if (isTrnEmpty(trn)) {
			throw new TransactionException(HttpStatus.NO_CONTENT);
		}
		
	}

	private boolean isTrnEmpty(RhymeData trn) {
		return trn.getWords().length < 0;
	}

	
}
