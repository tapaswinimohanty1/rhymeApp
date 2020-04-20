/**
 * 
 */
package com.sc.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sc.dto.Statistic;
import com.sc.service.StatisticService;

/**
 * @author tapaswini
 *
 */
@RestController
@RequestMapping(value = "/statistics")
public class StatisticController {

	@Autowired
	private StatisticService statisticService;

	@Transactional
	@GetMapping("/count")
	public ResponseEntity<?> getStatistics() {
		LocalDateTime now = LocalDateTime.now();
        return new ResponseEntity<Statistic>(statisticService.getStatistics(now), HttpStatus.OK) ;

	}

}
