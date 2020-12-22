package com.sms.msgsend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * desc 系统权限控制
 * @author ${user}
 * @date 2020/12/18
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemAccessControl {

    /**
     * 描述操作 例:Xxx管理-执行Xxx操作
     * @return
     */
    String description() default "";
}
