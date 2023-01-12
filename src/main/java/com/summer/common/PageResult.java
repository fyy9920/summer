package com.summer.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tubo
 * 自定义响应类
 * @date 2022/12/15
 */
public class PageResult<T> {

    public static <T> Map<String, Object> success(IPage<T> page) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", page.getRecords());
        resultMap.put("total", page.getTotal());
        resultMap.put("pageTotal", page.getPages());
        resultMap.put("current", page.getCurrent());
        resultMap.put("size", page.getSize());
        return resultMap;
    }
}
