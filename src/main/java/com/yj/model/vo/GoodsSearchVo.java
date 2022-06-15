package com.yj.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "商品搜索Vo")
public class GoodsSearchVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页数: 起始页数为1", required = true, example = "1")
    private Integer pageNumber;

    @ApiModelProperty(value = "页面大小(-1可查所有)", required = true, example = "10")
    private Integer pageSize;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品分类")
    private String goodsType;

    @ApiModelProperty(value = "商品价格开始")
    private BigDecimal goodsPriceStart;

    @ApiModelProperty(value = "商品价格结束")
    private BigDecimal goodsPriceEnd;
}
