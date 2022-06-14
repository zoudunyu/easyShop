package com.yj.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "排序参数")
public class OrderParam {

    @ApiModelProperty(value = "参数名", required = true)
    private String name;
    @ApiModelProperty(value = "是否倒序")
    private Boolean isDesc = false;
}
