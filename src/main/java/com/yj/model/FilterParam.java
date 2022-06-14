package com.yj.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "过滤参数")
public class FilterParam {
    public static final String TYPE_STRING = "String";
    public static final String TYPE_INTEGER = "Integer";
    public static final String TYPE_LONG = "Long";
    public static final String TYPE_DOUBLE = "Double";
    public static final String TYPE_BIGDECIMAL = "BigDecimal";
    public static final String TYPE_DATE = "Date";
    public static final String TYPE_BOOLEAN = "Boolean";

    public static final String CONDITION_EQUAL = "equal";
    public static final String CONDITION_LIKE = "like";
    public static final String CONDITION_IN = "in";
    public static final String CONDITION_IS_NULL = "is_null";
    public static final String CONDITION_NOT_EQUAL = "not_equal";
    public static final String CONDITION_NOT_LIKE = "not_like";
    public static final String CONDITION_NOT_IN = "not_in";
    public static final String CONDITION_NOT_NULL = "not_null";
    public static final String CONDITION_GREATER_THAN = "greater_than";
    public static final String CONDITION_GREATER_EQUAL_THAN = "greater_equal_than";
    public static final String CONDITION_LESS_THAN = "less_than";
    public static final String CONDITION_LESS_EQUAL_THAN = "less_equal_than";

    @ApiModelProperty(value = "参数名", required = true)
    private String name;
    @ApiModelProperty(value = "参数类型", allowableValues = "String,Integer,Long,Double,BigDecimal,Date,Boolean")
    private String type = TYPE_STRING;
    @ApiModelProperty(value = "条件", required = true,
            allowableValues = "equal,like,in,is_null,not_equal,not_like,not_in,not_null,greater_than,greater_equal_than,less_than,less_equal_than")
    private String condition;
    @ApiModelProperty(value = "参数值", required = true)
    private String value;
}
