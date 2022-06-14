package com.yj.dao.master.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.dao.master.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表 Mapper 接口
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {}
