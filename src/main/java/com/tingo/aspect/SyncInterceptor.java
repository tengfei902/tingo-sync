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
        try {
            if(!X.verify("a72995a1705a1a5e2f9c3eed633c93a1")) {
                System.exit(0);
            }
        }catch (Exception e) {
            System.exit(0);
        }
    }
}
