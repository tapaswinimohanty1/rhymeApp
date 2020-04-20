package com.sc.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class AppInitService {
	private static Logger logger = LoggerFactory.getLogger(AppInitService.class);

	private List<String> sampleInput =null;

	public AppInitService() {
		sampleInput=new ArrayList<>();
	}


	public void init() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();

			File file = new File(classLoader.getResource("file.txt").getFile());

			sampleInput = FileUtils.readLines(file, "utf-8");
		} catch (IOException e) {
			//throw new ParseException();
			logger.error("Unable to parse file", e);
		}

	}
	public List<String> sampleInput(){
		return sampleInput;
	}

	public void destroy() {
		sampleInput.clear();
		System.out.println("Inside destroy method - "+sampleInput);
	}
}
