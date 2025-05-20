package com.root.mybatis.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForeignKey {
    String tableName() default "";

    String tableColumn() default "";
}
