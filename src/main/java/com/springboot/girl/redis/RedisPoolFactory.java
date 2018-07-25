/*package com.springboot.girl.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {

	@Autowired
	RedisConfig redisConfig;*/

/**
	 * 原来我们是在 spring.xml文件中配置JedisPool
	 * 现在用@Bean注解来声明一个bean
	 * @return
	 *//*

	@Bean(name="jedisPool")
	public JedisPool jedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
		poolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getTimeBetweenEvictionRunsMillis());
		poolConfig.setMinEvictableIdleTimeMillis(redisConfig.getMinEvictableIdleTimeMillis());
		poolConfig.setTestWhileIdle(redisConfig.isTestWhileIdle());
		poolConfig.setSoftMinEvictableIdleTimeMillis(redisConfig.getSoftMinEvictableIdleTimeMillis());
		poolConfig.setBlockWhenExhausted(redisConfig.isBlockWhenExhausted());
		poolConfig.setNumTestsPerEvictionRun(redisConfig.getNumTestsPerEvictionRun());
		poolConfig.setTestOnBorrow(redisConfig.isTestOnBorrow());
		JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
				redisConfig.getTimeout()*1000, redisConfig.getPassword());
		return jp;
	}

	@Bean(name = "redisService")
	public RedisService redisService(@Qualifier("jedisPool") JedisPool jedisPool) {
		RedisService redisService = new RedisService();
		redisService.setJedisPool(jedisPool);
		return  redisService;
	}


}
*/
