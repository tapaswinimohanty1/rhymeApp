package com.sc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

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
import com.sc.dto.Statistic;
import com.sc.service.StatisticService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StasticControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Autowired
	private StatisticService service;

	private RhymeRequest rhymeRequest;
	@Before
	public void before() {
		rhymeRequest = new RhymeRequest();
	}
	@Test
	public void postCustomerShouldReturnEmptyInCaseOfNoMatch() {
		rhymeRequest.setWords(new String[]{"abc","test"});
		ResponseEntity<Statistic> beforecount =  template.getForEntity("/statistics/count",Statistic.class);
		template.getForEntity("/rhymes/allRyhmeList",Map.class);
		ResponseEntity<Statistic> afterCount =  template.getForEntity("/statistics/count",Statistic.class);
		assertEquals(beforecount.getBody().getHit_count().incrementAndGet(), afterCount.getBody().getHit_count().longValue());;

	}

}
