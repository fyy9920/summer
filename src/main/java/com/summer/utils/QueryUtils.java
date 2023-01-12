package com.summer.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.summer.annotation.Query;
import com.summer.enums.QueryMethodEnum;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author tubo
 * @date 2022/12/28
 */
public class QueryUtils {
    public static <T, E> QueryWrapper<T> getQueryWrapper(E entity) throws IllegalAccessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        List<Field> fields = getAllFieldsWithRoot(entity.getClass());
        String sort = StringPool.EMPTY;
        String order = StringPool.EMPTY;
        for (Field field : fields) {
            field.setAccessible(true);
            Query query = field.getAnnotation(Query.class);
            if (query == null) {
                continue;
            }

            Object value = field.get(entity);
            if (QueryMethodEnum.SORT == query.type()) {
                if (ObjectUtil.isEmpty(value)) {
                    sort = "create_time";
                } else {
                    SqlInjectionUtils.filterContent(String.valueOf(value));
                    sort = String.valueOf(value);
                }
                continue;
            }
            if (QueryMethodEnum.ORDER == query.type()) {
                if (ObjectUtil.isEmpty(value)) {
                    order = "desc";
                } else {
                    SqlInjectionUtils.filterContent(String.valueOf(value));
                    order = String.valueOf(value);
                }
                continue;
            }

            // 如果当前值为空则跳
            if (ObjectUtil.isNull(value) || StringUtils.EMPTY.equals(value)) {
                continue;
            }

            SqlInjectionUtils.filterContent(String.valueOf(value));

            String attributeName = StringUtils.isBlank(query.attributeName()) ? field.getName() : query.attributeName();
            String column = query.automaticMark() ? StrUtil.toUnderlineCase(attributeName) : attributeName;

            switch (query.type()) {
                case NE:
                    queryWrapper.ne(column, value);
                    break;
                case GT:
                    queryWrapper.gt(column, value);
                    break;
                case GE:
                    queryWrapper.ge(column, value);
                    break;
                case LT:
                    queryWrapper.lt(column, value);
                    break;
                case LE:
                    queryWrapper.le(column, value);
                    break;
                case BETWEEN:
                    if (ObjectUtil.isNotNull(value) && value instanceof ArrayList) {
                        List<Object> between = ListUtil.toList(value);
                        if (CollectionUtil.isNotEmpty(between) && between.size() > 2) {
                            queryWrapper.between(column, between.get(0), between.get(1));
                        }
                    }
                    break;
                case LIKE:
                    queryWrapper.like(column, value);
                    break;
                case LEFT_LIKE:
                    queryWrapper.likeLeft(column, value);
                    break;
                case RIGHT_LIKE:
                    queryWrapper.likeRight(column, value);
                    break;
                case IN:
                    if (ObjectUtil.isNotNull(value) && value instanceof ArrayList) {
                        queryWrapper.in(column, CollectionUtil.toList(value));
                    }
                    break;
                case NOT_IN:
                    if (ObjectUtil.isNotNull(value) && value instanceof ArrayList) {
                        queryWrapper.notIn(column, CollectionUtil.toList(value));
                    }
                    break;
                case IS_NULL:
                    queryWrapper.isNull(column);
                    break;
                case IS_NOT_NULL:
                    queryWrapper.isNotNull(column);
                    break;
                default:
                    queryWrapper.eq(column, value);
                    break;
            }
        }
        if (StringUtils.isNotEmpty(sort)) {
            if ("desc".equalsIgnoreCase(order)) {
                queryWrapper.orderByDesc(sort);
            } else {
                queryWrapper.orderByAsc(sort);
            }
        }

        return queryWrapper;
    }

    private static List<Field> getAllFieldsWithRoot(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();

        //获取本类所有字段
        Field[] dFields = clazz.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(dFields)) {
            fieldList.addAll(Arrays.asList(dFields));
        }

        // 若父类是Object，则直接返回当前Field列表
        Class<?> superClass = clazz.getSuperclass();
        if (superClass == Object.class) {
            return Arrays.asList(dFields);
        }

        // 递归查询父类的field列表
        List<Field> superFields = getAllFieldsWithRoot(superClass);

        if (CollectionUtil.isNotEmpty(superFields)) {
            superFields.stream().
                    filter(field -> !fieldList.contains(field)).
                    forEach(fieldList::add);
        }
        return fieldList;
    }
}