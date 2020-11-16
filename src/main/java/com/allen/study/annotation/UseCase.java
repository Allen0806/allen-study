/**
 * 
 */
package com.allen.study.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 注解测试
 *
 * @author Allen
 * @date 2019年12月25日
 * @since
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface UseCase {

	int id();

	String description() default "";
}
