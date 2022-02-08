package com.kevin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {

    int limitNum() default 10;//请求数/s

    boolean ipRestricted() default true; //是否限制同一IP

    int ipLimitNum() default 10;//同一IP访问的次数/s
}
