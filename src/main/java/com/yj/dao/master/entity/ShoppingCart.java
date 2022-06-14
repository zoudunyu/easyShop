package com.yj.dao.master.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shopping_cart")
@ApiModel(value = "ShoppingCart对象", description = "购物车表")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="购物车id", example = "")
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    @ApiModelProperty(value="用户id", example = "")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value="商品id", example = "")
    @TableField("goods_id")
    private Integer goodsId;

    @ApiModelProperty(value="状态,1：正常，0：已删除", example = "")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value="添加时间", example = "")
    @TableField("create_time")
    private Long createTime;

    @ApiModelProperty(value="备注", example = "")
    @TableField("remark")
    private String remark;


}
