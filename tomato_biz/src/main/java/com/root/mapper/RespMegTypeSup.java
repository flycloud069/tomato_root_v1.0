package com.root.mapper;

import java.lang.annotation.*;

/**

 * 在某个类上添加了该注解后，将不会自动注入到需要被获取的service中

 * @author Lvbey

 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface RespMegTypeSup {
    public String value() default "未知错误";
}


