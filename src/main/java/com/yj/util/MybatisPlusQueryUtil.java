package com.yj.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.yj.model.FilterParam;
import com.yj.model.OrderParam;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MybatisPlus查询工具
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:48:18
 */
public class MybatisPlusQueryUtil {

    /**
     * 初始化查询wrapper
     *
     * @param wrapper
     * @param clazz
     * @param filterParamList
     * @param orderParamList
     * @return
     * @author 邹敦宇
     * @date 2021-12-08 11:17:50
     **/
    public static <T> void initQueryWrapper(QueryWrapper<T> wrapper, Class<T> clazz,
            List<FilterParam> filterParamList, List<OrderParam> orderParamList) {
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(clazz);
        handleFilterParam(wrapper, columnMap, filterParamList);
        handleOrderParam(wrapper, columnMap, orderParamList);
    }

    /**
     * 处理过滤条件
     *
     * @param wrapper
     * @param columnMap
     * @param filterParamList
     * @return
     * @author 邹敦宇
     * @date 2021-12-08 11:29:54
     **/
    private static <T> void handleFilterParam(QueryWrapper<T> wrapper, Map<String,
            ColumnCache> columnMap, List<FilterParam> filterParamList) {
        for (FilterParam filterParam : filterParamList) {
            String column = columnMap.get(LambdaUtils.formatKey(filterParam.getName())).getColumn();
            String type = filterParam.getType();
            String condition = filterParam.getCondition();
            String valueStr = filterParam.getValue();
            Object value = valueStr;
            switch (type) {
                case FilterParam.TYPE_DATE:
                    value = DateUtil.parseDate(valueStr, "yyyy-MM-dd HH:mm:ss");
                    break;
                case FilterParam.TYPE_BOOLEAN:
                    value = Boolean.parseBoolean(valueStr);
                    break;
                default:
                    break;
            }
            if (condition != null) {
                if (condition.endsWith(FilterParam.CONDITION_EQUAL)) {
                    if (FilterParam.CONDITION_NOT_EQUAL.equals(condition)) {
                        wrapper.ne(column, value);
                    } else {
                        wrapper.eq(column, value);
                    }
                } else if (condition.endsWith(FilterParam.CONDITION_LIKE)) {
                    if (FilterParam.CONDITION_NOT_LIKE.equals(condition)) {
                        wrapper.notLike(column, value);
                    } else {
                        wrapper.like(column, value);
                    }
                } else if (condition.endsWith(FilterParam.CONDITION_IN)) {
                    List<?> list;
                    switch (type) {
                        case FilterParam.TYPE_INTEGER:
                            list = Arrays.stream(valueStr.split(",")).map(Integer::parseInt).collect(
                                    Collectors.toList());
                            break;
                        case FilterParam.TYPE_LONG:
                            list = Arrays.stream(valueStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
                            break;
                        case FilterParam.TYPE_DOUBLE:
                            list = Arrays.stream(valueStr.split(",")).map(Double::parseDouble).collect(
                                    Collectors.toList());
                            break;
                        case FilterParam.TYPE_BIGDECIMAL:
                            list = Arrays.stream(valueStr.split(",")).map(BigDecimal::new).collect(Collectors.toList());
                            break;
                        default:
                            list = Arrays.asList(valueStr.split(","));
                            break;
                    }
                    if (!list.isEmpty()) {
                        if (FilterParam.CONDITION_NOT_IN.equals(condition)) {
                            wrapper.notIn(column, list);
                        } else {
                            wrapper.in(column, list);
                        }
                    }
                } else if (FilterParam.CONDITION_IS_NULL.equals(condition)) {
                    wrapper.isNull(column);
                } else if (FilterParam.CONDITION_NOT_NULL.equals(condition)) {
                    wrapper.isNotNull(column);
                } else if (FilterParam.CONDITION_GREATER_THAN.equals(condition)) {
                    wrapper.gt(column, value);
                } else if (FilterParam.CONDITION_GREATER_EQUAL_THAN.equals(condition)) {
                    wrapper.ge(column, value);
                } else if (FilterParam.CONDITION_LESS_THAN.equals(condition)) {
                    wrapper.lt(column, value);
                } else if (FilterParam.CONDITION_LESS_EQUAL_THAN.equals(condition)) {
                    wrapper.le(column, value);
                }
            }
        }
    }

    /**
     * 处理排序条件
     *
     * @param wrapper
     * @param columnMap
     * @param orderParamList
     * @return
     * @author 邹敦宇
     * @date 2021-12-08 11:30:00
     **/
    private static <T> void handleOrderParam(QueryWrapper<T> wrapper, Map<String,
            ColumnCache> columnMap, List<OrderParam> orderParamList) {
        for (OrderParam orderParam : orderParamList) {
            String column = columnMap.get(LambdaUtils.formatKey(orderParam.getName())).getColumn();
            if (orderParam.getIsDesc()) {
                wrapper.orderByDesc(column);
            } else {
                wrapper.orderByAsc(column);
            }
        }
    }
}
