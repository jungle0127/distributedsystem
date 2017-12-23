package org.psredis.jedis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class JedisDemoService {
	@Value("${redis.server}")
	private String redisHost;
	@Value("${redis.port}")
	private int redisPort;
	
	private Jedis jedisClient;
	
	public void set(String key, String value) {
		this.jedisClient = new Jedis(this.redisHost, this.redisPort);
		jedisClient.set(key, value);
		this.jedisClient.close();
	}
	
	public String get(String key) {
		this.jedisClient = new Jedis(this.redisHost, this.redisPort);
		String value = this.jedisClient.get(key);
		this.jedisClient.close();
		return value;
	}
}
