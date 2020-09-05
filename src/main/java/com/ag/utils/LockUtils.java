package com.ag.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Objects;

//todo--
@UtilityClass
public class LockUtils {
    public boolean tryLock(RedisTemplate<String, Object> redisTemplate, String key, Duration duration) {

        return (Boolean) redisTemplate.execute((RedisCallback) connection->{

            long expire = System.currentTimeMillis() + duration.toMillis();
            Boolean acquire = connection.setNX(key.getBytes(), String.valueOf(expire).getBytes());
            if(acquire){
                return true;
            }
            byte[] bytes = connection.get(key.getBytes());
            if(Objects.nonNull(bytes)&&bytes.length>0){
                long expireTime = Long.parseLong(new String(bytes));
                if(expireTime<System.currentTimeMillis()){
                    connection.getSet(key.getBytes(),String.valueOf(expire).getBytes() );
                    return true;
                }
            }
            return false;
        });


    }

    public static void release(RedisTemplate<String, Object> redisTemplate, String key) {
         redisTemplate.delete(key);
    }
}
