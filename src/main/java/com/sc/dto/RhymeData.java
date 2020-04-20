/**
 * 
 */
package com.sc.dto;

/**
 * @author tapaswini
 *
 */
public class RhymeData {
	private String[] words;

	public RhymeData() {

	}

	public RhymeData(String[] words) {
		this.words = words;
	}

	public RhymeData(RhymeData transactionData) {
		this.words = transactionData.words;
	}

	public String[] getWords() {
		return words;
	}

	public void setWords(String[] words) {
		this.words = words;
	}


}
