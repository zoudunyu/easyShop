package com.yj.dao.master.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品表
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
@ApiModel(value = "Goods对象", description = "商品表")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="商品id", example = "")
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Integer goodsId;

    @ApiModelProperty(value="商品名称", example = "")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty(value="商品售价", example = "")
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value="商品简介", example = "")
    @TableField("goods_introduction")
    private String goodsIntroduction;

    @ApiModelProperty(value="商品图片路径，多张图片用,隔开", example = "")
    @TableField("goods_picture")
    private String goodsPicture;

    @ApiModelProperty(value="商品库存", example = "")
    @TableField("goods_stock")
    private Integer goodsStock;

    @ApiModelProperty(value="备注", example = "")
    @TableField("remark")
    private String remark;


}
