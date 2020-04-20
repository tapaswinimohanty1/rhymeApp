/**
 * 
 */
package com.sc.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sc.dto.RhymeData;
import com.sc.dto.RhymeRequest;
import com.sc.exception.ParseException;

/**
 * @author tapaswini
 *
 */
@Component
public class RhymeDataParser {
	private static Logger logger = LoggerFactory.getLogger(RhymeDataParser.class);

	public RhymeData parseRequest(RhymeRequest trn) throws ParseException {
		try {
			String[] newWords = new String[trn.getWords().length];
			System.arraycopy(trn.getWords(), 0, newWords, 0, trn.getWords().length);

			return new RhymeData(newWords);
		} catch (Exception e) {
			logger.error("Could not parse request",e.getCause());
			throw new ParseException();
		}
	}
}
