package com.asiainfo.provider.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisConfig implements InitializingBean {

    private JedisPool jedisPool;

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(20);
        config.setMinIdle(20);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        jedisPool = new JedisPool(config, "39.102.32.49", 7001, 1000 * 2, null, null, 0);
    }

    public Jedis getResource() {
        return jedisPool.getResource();
    }
}
