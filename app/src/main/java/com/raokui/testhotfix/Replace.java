package com.raokui.testhotfix;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 饶魁 on 2017/12/18.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Replace {
    String clazz();// 修复的class

    String method();// 修复的方法

}