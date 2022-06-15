package com.yj.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("修改购物车实体类")
public class EditShoppingCartVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车ID")
    @NotNull(message = "购物车id不能为空")
    private Integer cartId;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @ApiModelProperty("商品ID")
    @NotNull(message = "商品id不能为空")
    private Integer goodsId;

    @ApiModelProperty("商品数量")
    @NotNull(message = "商品数量")
    private Integer goodsNum;

    @ApiModelProperty(value = "添加时间", example = "")
    @NotNull(message = "未知错误")
    @TableField("create_time")
    private Long createTime;

    @ApiModelProperty(value = "备注", example = "")
    @TableField("remark")
    private String remark;
}
