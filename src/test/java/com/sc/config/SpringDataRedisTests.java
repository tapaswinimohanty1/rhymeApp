package com.sc.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringDataRedisTests {

    @Autowired
    RedisOperations<String, String> redisOperations;

    @Test
    public void contextLoads() {

        redisOperations.opsForValue().set("key", "value");
        assertEquals("value", redisOperations.opsForValue().get("key"));
    }


}

