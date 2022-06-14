package com.yj.dao.master.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 用户表
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
@TableName("user_info")
@ApiModel(value = "UserInfo对象", description = "用户表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户id", example = "")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value="用户名", example = "")
    @TableField("username")
    private String username;

    @ApiModelProperty(value="密码", example = "")
    @TableField("password")
    private String password;

    @ApiModelProperty(value="姓名", example = "")
    @TableField("name")
    private String name;

    @ApiModelProperty(value="用户类别，1：管理员，2：普通用户", example = "")
    @TableField("user_type")
    private Integer userType;

    @ApiModelProperty(value="用户余额", example = "")
    @TableField("balance")
    private BigDecimal balance;

    @ApiModelProperty(value="创建时间", example = "")
    @TableField("create_time")
    private Long createTime;

    @ApiModelProperty(value="备注", example = "")
    @TableField("remark")
    private String remark;


}
