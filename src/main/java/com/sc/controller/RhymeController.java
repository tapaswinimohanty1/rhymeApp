/**
 * 
 */
package com.sc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.dto.RhymeData;
import com.sc.dto.RhymeRequest;
import com.sc.exception.ParseException;
import com.sc.exception.TransactionException;
import com.sc.parser.RhymeDataParser;
import com.sc.service.RhymeService;
import com.sc.validator.RhymeDataValidator;

/**
 * @author tapaswini
 *
 */
@RestController
@RequestMapping(value = "/rhymes")
public class RhymeController {
	private static Logger logger = LoggerFactory.getLogger(RhymeController.class);

	@Autowired
	private RhymeDataValidator rhymeDataValidator;

	@Autowired
	private RhymeDataParser rhymeDataParser;

	@Autowired
	private RhymeService rhymeService;
	private Map<String,List> rhymedList = new HashMap<String, List>();

	@Transactional
	@PostMapping("/all")
	public  ResponseEntity<?> findRhymes(@RequestBody RhymeRequest rhymeRequest)
			throws ParseException {
		RhymeData rhymeData = rhymeDataParser.parseRequest(rhymeRequest);
		try {
			rhymeDataValidator.validate(rhymeData);
		}catch(TransactionException ex) {
			return new ResponseEntity<>(ex.getStatus());
		}
		rhymedList  = rhymeService.findRhymedWords(rhymeData);
        return new ResponseEntity<Map<String, List>>(rhymedList, HttpStatus.OK) ;
	}

	@GetMapping("/allRyhmeList")
	public  ResponseEntity<?> findAllRhymesList()
			throws ParseException {	
    return new ResponseEntity<Map<String, List>>(rhymedList, HttpStatus.OK) ;
	}
	
}
