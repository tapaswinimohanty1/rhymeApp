package com.sc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sc.dto.RhymeData; 
@Component
public class RhymeFinder { 

	public Map<String ,List> findRhymedWords(List<String> sampleInputList,RhymeData trnData){
		
		List<String> words = sampleInputList;
	    
		Map<String,List> rhymeMap = new HashMap<String, List>();

        Trie dict = new Trie(); 
		for(String sampleInput : sampleInputList ) {
	        dict.insert(sampleInput); 
		}

       for(String input : trnData.getWords()) {
    	   String result = dict.getMatchingSuffix(input);    
           List<String> rhymedWords = null;
           if(!result.isBlank()) {
		   rhymedWords = words.stream()
					.filter(str -> str.endsWith(result))
					.collect(Collectors.toList()); 
           }
			rhymeMap.put(input,rhymedWords);

       }
       
     return rhymeMap;  
	}


}

