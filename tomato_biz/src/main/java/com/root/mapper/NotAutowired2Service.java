package com.root.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**

 * 在某个类上添加了该注解后，将不会自动注入到需要被获取的service中

 * @author Lvbey

 */

@Target({ElementType.TYPE})

@Retention(RetentionPolicy.RUNTIME)

public @interface NotAutowired2Service {
}
