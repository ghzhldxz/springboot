package com.springboot.girl.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
	private JedisPool jedisPool;

    /**
	 * 获取当个对象
	 * */
	public <T> T get(KeyPrefix prefix, String key,  Class<T> clazz) {
		 Jedis jedis = null;
		 try {
			 jedis =  jedisPool.getResource();
			 //生成真正的key
			 String realKey  = prefix.getPrefix() + key;
			 String  str = jedis.get(realKey);
			 T t =  stringToBean(str, clazz);
			 return t;
		 }finally {
			  returnToPool(jedis);
		 }
	}

	public boolean del(KeyPrefix prefix,String key) {
	    Jedis jedis = null;
	    try {
	        jedis = jedisPool.getResource();
	        String realKey = prefix.getPrefix() + key;
	        Long ret = jedis.del(realKey);
	        if(ret>0) {
	            return true;
            }
            return false;
        } finally {
	        returnToPool(jedis);
        }
    }
	
	/**
	 * 设置对象
	 * */
	public <T> boolean set(KeyPrefix prefix, String key,  T value) {
		Jedis jedis = null;
		try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String valStr = this.beanToString(value);
            if(valStr == null || "".equals(valStr.trim())) {
                return false;
            }
            if(prefix.expireSeconds()>0 ) {
                jedis.setex(realKey,prefix.expireSeconds(),valStr);
            } else {
                jedis.set(realKey,valStr);
            }
            return true;
        }finally {
		    returnToPool(jedis);
        }
	}

    /**
     * 判断key是否存在
     * */
    public boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * */
    public Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * */
    public Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
	
	public static <T> String beanToString(T value) {
		return JSON.toJSONString(value);
	}

	public <T> T stringToBean(String str, Class<T> clazz) {
		if(str == null || "".equals(str) || clazz == null) {
			return null;
		}
		return JSON.parseObject(str,clazz);
	}

	private void returnToPool(Jedis jedis) {
		 if(jedis != null) {
			 jedis.close();
		 }
	}

}
