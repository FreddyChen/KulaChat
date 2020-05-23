package com.freddy.kulachat.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:39
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
