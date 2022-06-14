package com.yj.dao.master.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.dao.master.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表 Mapper 接口
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:48
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {}
