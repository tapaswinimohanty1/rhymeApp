package com.sc.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("rateLimiter")
public class RateLimiterImpl {
	private static Logger logger = LoggerFactory.getLogger(RateLimiterImpl.class);
	private static final Integer DEFAULT_REDIS_EXPIRATION_DURATION = 5; //minutes

	@Autowired
	private StringRedisTemplate template;

	@Autowired
	private ObjectMapper mapper;

	public long getCurrent(final Rate rate,boolean increaseCount){		

		LocalDateTime now = LocalDateTime.now();
		Integer elapsedSeconds = now.getSecond();
		Integer currentMinute = now.getMinute();
		Long  requestCountLastMinute = 0L;
		Long requestCountCurrentMinute = 0L;
		// get modulo 60 so at 0 minute we get 59 (from the previous hour)
		Integer lastMinute = (currentMinute + 59 ) % 60;
		String requestCount = template.opsForValue().get(constructRedisKey(rate.getKey(),lastMinute)); 

		if (requestCount == null) {
			requestCountLastMinute = 0L;
		}
		else {
			requestCountLastMinute = Long.valueOf(requestCount);

		}

		// increase the count for current minute
		if (increaseCount) {

			requestCountCurrentMinute = template.opsForValue().increment(constructRedisKey(rate.getKey(), currentMinute));
		}
		// set expiration for the key as Redis does not support setting timeout together with increment operation
		template.expire(constructRedisKey(rate.getKey(), currentMinute), DEFAULT_REDIS_EXPIRATION_DURATION, TimeUnit.MINUTES);


		return requestCountCurrentMinute;

	}

	private String constructRedisKey(String key, Integer minute) {
		return String.format("count#%s#%d", key, minute);
	}


	public long getRate(String apikey,boolean increaseCount){
		Rate rate = null;
		long count = 0L;
		try {
			rate = mapper.readValue(template.opsForValue().get(apikey), Rate.class);
			count = getCurrent(rate,increaseCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	public void setRate(Rate rate){
		try {
			template.opsForValue().set(rate.getKey(), mapper.writeValueAsString(rate));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

