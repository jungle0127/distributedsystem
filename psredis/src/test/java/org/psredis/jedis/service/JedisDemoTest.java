package org.psredis.jedis.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisDemoTest {
	
	@Autowired
	private JedisDemoService jedisService;
	
	@Test
	public void testGet() {
		this.jedisService.set("ps", "lotus");
		Assert.assertEquals("lotus", this.jedisService.get("ps"));
	}
	@Test
	public void testJedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(5);
		poolConfig.setMaxTotal(30);
		JedisPool pool = new JedisPool(poolConfig, "192.168.1.4", 6379);
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set("key", "value");
			String value = jedis.get("key");
			Assert.assertEquals(value, "value");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			pool.close();
			jedis.close();
		}
		
	}
}
