package com.asiainfo.provider.redis;

import com.alibaba.dubbo.config.annotation.Service;
import com.asiainfo.api.redis.RedisService;
import com.asiainfo.common.Constant;
import com.asiainfo.provider.config.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

@Service
public class JedisServiceImpl implements RedisService {

    // 锁过期时间 10s
    private final long INTERNAL_LOCK_LEASE_TIME = 10000;

    // 锁设置参数
    private final SetParams params = SetParams.setParams().nx().px(INTERNAL_LOCK_LEASE_TIME);


    @Autowired
    private JedisConfig jedisConfig;

    @Override
    public boolean lock(String key) {
        Jedis jedis = jedisConfig.getResource();
        String isSuccess = jedis.set(key, "redis_lock", params);
        jedis.close();
        return Constant.Flag.SUCCESS.equals(isSuccess);
    }

    @Override
    public void unlock(String key) {
        Jedis jedis = jedisConfig.getResource();
        jedis.del(key);
        jedis.close();
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisConfig.getResource();
        String reVal = jedis.get(key);
        jedis.close();
        return reVal;
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = jedisConfig.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    @Override
    public void setExpire(String key, String value, int expireTime) {
        Jedis jedis = jedisConfig.getResource();
        jedis.setex(key, expireTime, value);
        jedis.close();
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = jedisConfig.getResource();
        Long time = jedis.ttl(key);
        jedis.close();
        return time;
    }
}
