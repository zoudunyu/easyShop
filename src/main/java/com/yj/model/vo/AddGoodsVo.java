package com.yj.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Goods对象", description = "添加商品VO")
public class AddGoodsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称", example = "")
    @NotBlank(message = "商品名称不能为空")
    private String goodsName;

    @ApiModelProperty(value = "商品售价", example = "")
    @NotNull(message = "商品售价不能为空")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品简介", example = "")
    private String goodsIntroduction;

    @ApiModelProperty(value = "商品图片路径，多张图片用,隔开", example = "")
    private String goodsPicture;

    @ApiModelProperty(value = "商品分类", example = "")
    @NotNull(message = "商品分类不能为空")
    private Integer goodsType;

    @ApiModelProperty(value = "商品库存", example = "")
    @NotNull(message = "商品库存不能为空")
    private Integer goodsStock;

    @ApiModelProperty(value = "备注", example = "")
    private String remark;
}
