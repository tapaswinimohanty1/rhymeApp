package com.sc.interceptor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sc.dto.Statistic;
import com.sc.util.Rate;
import com.sc.util.RateLimiterImpl;

@Component
public class StatisticInterceptor implements HandlerInterceptor   {
	private static Logger logger = LoggerFactory.getLogger(StatisticInterceptor.class);

	@Value("${hitTimeLimit: #{60}}")
	private int hitTimeLimit;
	
	@Value("${appNameForRedis}")
	private String appNameForRedis;
	
	@Autowired
	private RateLimiterImpl rateLimiter;
	
	AtomicLong hitCounts = new AtomicLong();
	Map<String,AtomicInteger> countMap = new HashMap<String,AtomicInteger>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Before Handler execution");
		boolean increaseCount = true;
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.equals("/count")) {
			increaseCount = false;
		}
		Rate rate = new Rate();
		rate.setKey(appNameForRedis);
		rate.setWindow(hitTimeLimit);
		rateLimiter.setRate(rate);
		
		long count = rateLimiter.getRate(appNameForRedis,increaseCount);
		hitCounts = new AtomicLong(count);
		return true;



	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("---method executed---");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {	
	
		logger.info("---after method compelted---");

	}

	

	public Statistic getStatHitCounts(LocalDateTime since) {	
		return new Statistic(hitCounts);
	}
	
}
