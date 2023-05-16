package com.prprv.property.common.beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yoooum
 */
public class UpdateUtils {
    /**
     * 将 source 的属性值复制到给定的 target 中，忽略 null 和 id 属性
     * 注意：只要属性匹配，数据源类和目标源类就不必匹配。数据源 Bean 公开但目标源 Bean 未公开的任何 Bean 属性都将被静默忽略。
     *
     * @param source 数据源
     * @param target 目标源，从数据库查询出来的实体
     */
    public static void copyNotNull(Object source, Object target) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        emptyNames.add("id");
        String[] result = new String[emptyNames.size()];
        BeanUtils.copyProperties(source, target, emptyNames.toArray(result));
    }
}
