package com.summer.enums;

/**
 * @author tubo
 * @date 2022/12/28
 */
public enum QueryMethodEnum {
    /**
     * 等于
     */
    EQ,
    /**
     * 不等于
     */
    NE,
    /**
     * 大于
     */
    GT,
    /**
     * 大于等于
     */
    GE,
    /**
     * 小于
     */
    LT,
    /**
     * 小于等于
     */
    LE,
    /**
     * 两值之间 BETWEEN 值1 AND 值2
     */
    BETWEEN,
    /**
     * LIKE '%值%'
     */
    LIKE,
    /**
     * LIKE '%值'
     */
    LEFT_LIKE,
    /**
     * LIKE '值%'
     */
    RIGHT_LIKE,
    /**
     * IN ('值')
     */
    IN,
    /**
     * NOT IN ('值')
     */
    NOT_IN,
    /**
     * 值 IS NULL
     */
    IS_NULL,
    /**
     * 值 IS NOT NULL
     */
    IS_NOT_NULL,

    /**
     * 排序方式
     */
    ORDER,
    /**
     * 排序字段
     */
    SORT;

}
