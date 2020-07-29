package com.zoe.common.rw;

import com.zoe.common.rw.annotation.MasterSlaveEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-100)
/**
 * 将自定义的切面序号设置一个很小的值，保证排在spring数据库事务切面之前执行
 */
public class RWAspect {
    /**
     * 环绕通知 @ReadOnly
     */
    @Around("@annotation(com.zoe.common.rw.annotation.ReadOnly)")
    public Object readOnly(ProceedingJoinPoint point) {
        return doAround(MasterSlaveEnum.SLAVE.getName(), point);
    }

    /**
     * 环绕通知 @Master
     */
    @Around("@annotation(com.zoe.common.rw.annotation.Master)")
    public Object master(ProceedingJoinPoint point) {
        return doAround(MasterSlaveEnum.MASTER.getName(), point);
    }

    /**
     * 环绕通知 @Slave
     */
    @Around("@annotation(com.zoe.common.rw.annotation.Slave)")
    public Object slave(ProceedingJoinPoint point) {
        return doAround(MasterSlaveEnum.SLAVE.getName(), point);
    }

    private Object doAround(String name, ProceedingJoinPoint point) {
        try {
            DataSourceNameHolder.push(name);
            return point.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            DataSourceNameHolder.pop();
        }
    }
}
