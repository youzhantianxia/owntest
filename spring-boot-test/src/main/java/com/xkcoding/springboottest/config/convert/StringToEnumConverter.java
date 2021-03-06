package com.xkcoding.springboottest.config.convert;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.xkcoding.springboottest.constants.BaseEnum;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * <p>
 * 枚举编码 -> 枚举 转化器
 * </p>
 *
 * @package: com.xkcoding.springboottest.config.convert
 * @description: 枚举编码 -> 枚举 转化器
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 11:21
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {
    private Map<String, T> enumMap = Maps.newHashMap();

    public StringToEnumConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            enumMap.put(e.getCode().toString(), e);
        }
    }

    @Override
    public T convert(String source) {
        T t = enumMap.get(source);
        if (ObjectUtil.isNull(t)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return t;
    }
}
