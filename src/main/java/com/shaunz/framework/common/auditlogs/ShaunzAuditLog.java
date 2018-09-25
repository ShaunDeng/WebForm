package com.shaunz.framework.common.auditlogs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ShaunzAuditLog {
	String optType() default "";
	String functionId() default "";
}
