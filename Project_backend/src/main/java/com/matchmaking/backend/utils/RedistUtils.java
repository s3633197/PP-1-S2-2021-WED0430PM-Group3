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
            return true;
        }
        return false;
    }

    public boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    public void del(String key){
        if(key !=null){
            redisTemplate.delete(key);
        }
    }


    /**
     *  Store string in redis
     *
     * @param key  Can not be null
     * @param value
     * @param time
     * @return true = success, false = fail
     */
    public boolean set(String key,String value, long time){
        if(time > 0){
            redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    /**
     *  get value by key from redis cache
     * @param key
     * @return value
     */

    public Object get(String key){
        return key == null? null:redisTemplate.opsForValue().get(key);
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
