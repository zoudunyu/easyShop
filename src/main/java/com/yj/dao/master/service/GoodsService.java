package com.yj.dao.master.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dao.master.entity.Goods;
import com.yj.dao.master.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
 * 商品表 服务实现类
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@DS("master")
@Service
public class GoodsService extends BaseDaoServiceImpl<GoodsMapper, Goods> {}
