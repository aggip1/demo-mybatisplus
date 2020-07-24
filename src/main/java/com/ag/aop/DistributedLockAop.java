package com.ag.aop;

import com.ag.annotation.DistributedLock;
import com.ag.utils.LockUtils;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
@Slf4j
public class DistributedLockAop {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(lock)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, DistributedLock lock) {
        String key = lock.key();
        if (StringUtil.isNotEmpty(key)) {
            key = proceedingJoinPoint.getTarget().getClass().toString() + ":" + proceedingJoinPoint.getSignature().getName();
        }
        Duration timeout = Duration.ofMillis(lock.timeout());
        Object proceed = null;
        if (LockUtils.tryLock(redisTemplate, key, timeout)) {
            try {
                proceed = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                log.debug("分布式锁加锁以后逻辑处理失败");
            }finally {
                //释放锁
                LockUtils.release(redisTemplate,key);
            }
        }
        return proceed;

    }

}
