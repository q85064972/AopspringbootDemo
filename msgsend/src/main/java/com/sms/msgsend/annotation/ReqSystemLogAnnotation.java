package com.sms.msgsend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * desc 请求日志记录
 * @author ${user}
 * @date 2020/12/18
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqSystemLogAnnotation {

    /**
     * 描述操作 例:Xxx管理-执行Xxx操作
     * @return
     */
    String description() default "";
}
