package com.yj.dao.master.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 订单表
 * </p>
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="订单id", example = "")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value="用户id", example = "")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value="商品id，多件商品用,号隔开", example = "")
    @TableField("goods_ids")
    private String goodsIds;

    @ApiModelProperty(value="订单状态", example = "")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value="创建时间", example = "")
    @TableField("create_time")
    private Long createTime;

    @ApiModelProperty(value="结算时间", example = "")
    @TableField("settle_time")
    private Long settleTime;

    @ApiModelProperty(value="付款时间", example = "")
    @TableField("payment_time")
    private Long paymentTime;

    @ApiModelProperty(value="备注", example = "")
    @TableField("remark")
    private String remark;


}
