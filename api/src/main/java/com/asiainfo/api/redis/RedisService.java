package com.asiainfo.api.redis;

public interface RedisService {

    String get(String key);

    void set(String key, String value);

    // expireTime 单位秒
    void setExpire(String key, String value, int expireTime);

    long ttl(String key);

    boolean lock(String key);

    void unlock(String key);
}
