package com.yj.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.model.PageParam;
import com.yj.util.MybatisPlusQueryUtil;

/**
 * 封装mybatisplus的ServiceImpl
 * @author 邹敦宇
 * @version 1.0
 * @date 2022/5/6 16:22
 */
public class BaseDaoServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 获取列表
     *
     * @param pageParam
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.邹敦宇.dao.master.entity.TestExample>
     * @author 邹敦宇
     * @date 2021-12-07 17:38:20
     **/
    public Page<T> listPage(PageParam pageParam) {
        Page<T> page = new Page<>(pageParam.getPageNumber(), pageParam.getPageSize());
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        MybatisPlusQueryUtil.initQueryWrapper(wrapper, this.getEntityClass(), pageParam.getFilter(),
                pageParam.getOrder());
        return this.page(page, wrapper);
    }
}
