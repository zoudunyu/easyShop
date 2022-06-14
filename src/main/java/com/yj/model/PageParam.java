package com.yj.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页参数")
public class PageParam {

    @ApiModelProperty(value = "页数: 起始页数为1", required = true, example = "1")
    private Integer pageNumber;
    @ApiModelProperty(value = "页面大小(-1可查所有)", required = true, example = "10")
    private Integer pageSize;
    @ApiModelProperty(value = "参数过滤", example = "[]")
    private List<FilterParam> filter = new ArrayList<>();
    @ApiModelProperty(value = "排序", example = "[]")
    private List<OrderParam> order = new ArrayList<>();
}
