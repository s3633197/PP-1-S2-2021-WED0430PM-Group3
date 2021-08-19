package com.matchmaking.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedistUtils {

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean expire(String key,long time){
        if(time > 0){
            redisTemplate.expire(key,time, TimeUnit.SECONDS);
        }
        return true;
    }

    public boolean hset (String key,String item, Object value,long time){
        redisTemplate.opsForHash().put(key,item,value);
        if(time > 0){
            expire(key,time);
        }
        return true;
    }

    public Object get(String key,String item) {
        return key == null? null: redisTemplate.opsForHash().get(key,item);
    }
}
