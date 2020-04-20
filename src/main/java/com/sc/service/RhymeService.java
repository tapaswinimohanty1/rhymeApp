/**
 * 
 */
package com.sc.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.sc.dto.RhymeData;
import com.sc.util.RhymeFinder;

/**
 * @author tapaswini
 *
 */
@Service
public class RhymeService {
	private static Logger logger = LoggerFactory.getLogger(RhymeService.class);

	@Autowired
	private RhymeFinder rhymeFinder;


	@Autowired
	private AppInitService appInitService;


	@Bean(initMethod="init",destroyMethod="destroy")
	public AppInitService getAppInitService() {
		return new AppInitService();
	}

	public List<String> loadSampleInput() {
		List<String> sampleInput = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();

			File file = new File(classLoader.getResource("file.txt").getFile());
			sampleInput = FileUtils.readLines(file, "utf-8");
		} catch (IOException e) {
			//throw new ParseException();
			logger.error("Unable to parse file", e);
		}
		return sampleInput;
	}

	public Map<String, List> findRhymedWords(RhymeData trnData) {
		Map<String,List> rhymedWords = new HashMap<String,List>();	
		List<String> sampleInput = loadSampleInput();
		rhymedWords = rhymeFinder.findRhymedWords(sampleInput,trnData);
		return rhymedWords;
	}


}
