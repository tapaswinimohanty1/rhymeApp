/**
 * 
 */
package com.sc.dto;

/**
 * @author tapaswini
 *
 */
public class RhymeRequest {

	private String[] words;

	public RhymeRequest() {

	}

	public RhymeRequest(String[] words) {
		this.words = words;
	}

	public RhymeRequest(RhymeRequest transactionRequest) {
		this.words = transactionRequest.words;
	}

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}

	
	
	
}
