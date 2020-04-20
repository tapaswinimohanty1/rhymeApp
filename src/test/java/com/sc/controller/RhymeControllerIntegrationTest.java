package com.sc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sc.dto.RhymeRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RhymeControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	private RhymeRequest rhymeRequest;
	@Before
	public void before() {
		rhymeRequest = new RhymeRequest();
	}
	@Test
	public void postCustomerShouldReturnEmptyInCaseOfNoMatch() {
		rhymeRequest.setWords(new String[]{"abc","test"});
		ResponseEntity<Map> responseEntity =  template.postForEntity("/rhymes/all", rhymeRequest,Map.class);
		Map<String,List> response  = responseEntity.getBody();
		Map<String,List> sampleResponse = new HashMap<String, List>();
		sampleResponse.put("abc",null);
		sampleResponse.put("test",null);
		assertNull(response.get("abc"));
		assertNull(response.get("test"));

	}

	@Test
	public void postShouldReturnForValidMatch() {

		rhymeRequest.setWords(new String[] {"fighting", "signing"});
		ResponseEntity<Map> responseEntity =  template.postForEntity("/rhymes/all", rhymeRequest,Map.class);
		Map<String,List> response  = responseEntity.getBody();
		Map<String,List> sampleResponse = new HashMap<String, List>();
		List<String> outputList = new ArrayList<String>();
		outputList.add("shooting");
		sampleResponse.put("fighting",outputList);

		List<String> outputList2 = new ArrayList<String>();
		outputList2.add("singing");
		outputList2.add("dancing");
		outputList2.add("shooting");
		sampleResponse.put("signing",outputList2);
		
		Assertions.assertThat(response.get("fighting").equals(outputList));
		Assertions.assertThat(response.get("signing").equals(outputList2));

	}


}