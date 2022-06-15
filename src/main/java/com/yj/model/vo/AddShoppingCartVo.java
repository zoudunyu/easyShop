package com.yj.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("添加购物车实体类")
public class AddShoppingCartVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty("商品ID")
    @NotNull(message = "商品id不能为空")
    private Integer goodsId;

    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量")
    private Integer goodsNum;
}
