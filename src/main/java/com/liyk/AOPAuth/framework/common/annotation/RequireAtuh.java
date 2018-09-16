package com.liyk.AOPAuth.framework.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAtuh {
    String[] value();
    String type() default "Or";
}
