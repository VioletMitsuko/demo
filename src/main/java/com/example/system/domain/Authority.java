package com.example.system.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限控制注解
 * @author VioletMitsuko
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    /**
     * 是否拦截，true拦截，false不拦截
     * @return  .
     */
    boolean required() default true;

    /**
     * 存放权限标识
     * @return  .
     */
    String[] value() default "/";
}
