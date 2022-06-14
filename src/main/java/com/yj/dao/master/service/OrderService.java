package com.yj.dao.master.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dao.master.entity.Order;
import com.yj.dao.master.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * 订单表 服务实现类
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:48
 */
@DS("master")
@Service
public class OrderService extends BaseDaoServiceImpl<OrderMapper, Order> {}
