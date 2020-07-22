package com.zoe.common.rw.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;


/**
 * @author gaonl
 * @date 2020/07/17
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Slave {
}