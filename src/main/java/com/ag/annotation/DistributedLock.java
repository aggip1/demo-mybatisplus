package com.ag.annotation;

import java.lang.annotation.*;

@Documented
//注解作用于方法上
@Target(ElementType.METHOD)
//运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String key() default "";

    long timeout();
}
