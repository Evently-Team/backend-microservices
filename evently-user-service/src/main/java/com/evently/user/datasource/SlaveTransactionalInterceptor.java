package com.evently.user.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class SlaveTransactionalInterceptor {

    @Around("@annotation(com.evently.user.datasource.SlaveTransactional)")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        MasterSlaveRoutingDataSource.setSlaveDataSource();

        return pjp.proceed();
    }
}
