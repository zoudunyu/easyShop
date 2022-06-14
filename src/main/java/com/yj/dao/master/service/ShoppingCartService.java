package com.yj.dao.master.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dao.master.entity.ShoppingCart;
import com.yj.dao.master.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
 * 购物车表 服务实现类
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@DS("master")
@Service
public class ShoppingCartService extends BaseDaoServiceImpl<ShoppingCartMapper, ShoppingCart> {}
