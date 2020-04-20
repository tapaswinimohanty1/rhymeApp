/**
 * 
 */
package com.sc.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.sc.dto.RhymeData;

/**
 * @author tapaswini
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RhymeServiceTest {
	@Autowired
	private RhymeService rhymeService;
	private RhymeData rhymeRequest;

	@Test
	public void statisticsTest() {
		rhymeRequest = new RhymeData();

		rhymeRequest.setWords(new String[] {"fighting", "signing"});
		Map<String, List> rhymedwords = rhymeService.findRhymedWords(rhymeRequest);
		Map<String,List> sampleResponse = new HashMap<String, List>();
		List<String> outputList = new ArrayList<String>();
		outputList.add("shooting");
		sampleResponse.put("fighting",outputList);

		List<String> outputList2 = new ArrayList<String>();
		outputList2.add("singing");
		outputList2.add("dancing");
		outputList2.add("shooting");
		sampleResponse.put("signing",outputList2);

		Assertions.assertThat(rhymedwords.get("fighting").equals(outputList));
		Assertions.assertThat(rhymedwords.get("signing").equals(outputList2));
	}
}
