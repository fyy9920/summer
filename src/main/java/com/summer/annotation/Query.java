package com.summer.annotation;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.summer.enums.QueryMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tubo
 * @date 2022/12/28
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {
    String attributeName() default StringPool.EMPTY;

    String fuzzySearch() default StringPool.EMPTY;

    boolean encryptMark() default false;

    boolean automaticMark() default true;

    QueryMethodEnum type() default QueryMethodEnum.EQ;
}

