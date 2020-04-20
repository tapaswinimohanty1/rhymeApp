/**
 * 
 */
package com.sc.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.dto.Statistic;
import com.sc.interceptor.StatisticInterceptor;

/**
 * @author tapaswini
 *
 */
@Service
public class StatisticService {
	private static Logger logger = LoggerFactory.getLogger(StatisticService.class);

  @Autowired
  private StatisticInterceptor statisticInterceptor;
	
	public Statistic getStatistics(LocalDateTime since) {
		return statisticInterceptor.getStatHitCounts(since);
	}
}
