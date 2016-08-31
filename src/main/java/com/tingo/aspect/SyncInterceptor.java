package com.tingo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by user on 16/8/31.
 */
@Aspect
@Component
public class SyncInterceptor {

    @Pointcut("execution(public * com.tingo.dao..*.select*(..))")
    public void validate(){}

    @Before("validate()")
    public void before() {

    }
}
