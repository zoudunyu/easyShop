package com.yj.dao.master.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dao.master.entity.Goods;
import com.yj.dao.master.mapper.GoodsMapper;
import com.yj.exception.MyException;
import com.yj.model.FilterParam;
import com.yj.model.PageParam;
import com.yj.model.vo.AddGoodsVo;
import com.yj.model.vo.EditGoodsVo;
import com.yj.model.vo.GoodsSearchVo;
import com.yj.util.BeanUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品表 服务实现类
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@DS("master")
@Service
public class GoodsService extends BaseDaoServiceImpl<GoodsMapper, Goods> {

    /**
     * 新增商品
     *
     * @param addGoodsVo 添加商品签证官
     * @return boolean
     */
    public Boolean save(AddGoodsVo addGoodsVo) {
        return save(voToBean(addGoodsVo));
    }

    /**
     * 多条件分页查询
     *
     * @param goodsSearchVo 商品搜索签证官
     * @return {@link Page}<{@link Goods}>
     */
    public Page<Goods> listPage(GoodsSearchVo goodsSearchVo) {
        PageParam pageParam = voToPage(goodsSearchVo);
        return this.listPage(pageParam);
    }

    public Goods getById(Integer goodsId) {
        return validation(goodsId);
    }

    public Boolean delete(Integer goodsId) {
        validation(goodsId);
        return delete(goodsId);
    }

    public Boolean modify(EditGoodsVo editGoodsVo) {
        validation(editGoodsVo.getGoodsId());
        Goods goods = voToBean(editGoodsVo);
        return updateById(goods);
    }

    public Goods validation(Integer goodsId) {
        if (goodsId == null) {
            return null;
        }
        Goods goods = getById(goodsId);
        if (ObjectUtils.isEmpty(goods)) {
            throw new MyException("商品不存在", "商品异常", HttpStatus.NOT_FOUND);
        }
        return goods;
    }

    private Goods voToBean(AddGoodsVo addGoodsVo) {
        Goods goods = new Goods();
        goods.setGoodsName(addGoodsVo.getGoodsName());
        goods.setGoodsPrice(addGoodsVo.getGoodsPrice());
        goods.setGoodsIntroduction(addGoodsVo.getGoodsIntroduction());
        goods.setGoodsPicture(addGoodsVo.getGoodsPicture());
        goods.setGoodsStock(addGoodsVo.getGoodsStock());
        goods.setGoodsType(addGoodsVo.getGoodsType());
        goods.setRemark(addGoodsVo.getRemark());
        return goods;
    }

    private Goods voToBean(EditGoodsVo editGoodsVo) {
        Goods goods = new Goods();
        BeanUtil.copyNullProperties(editGoodsVo, goods);
        return goods;
    }

    private PageParam voToPage(GoodsSearchVo goodsSearchVo) {
        PageParam pageParam = new PageParam();
        pageParam.setPageNumber(goodsSearchVo.getPageNumber());
        pageParam.setPageSize(goodsSearchVo.getPageSize());
        List<FilterParam> filterParamList = new ArrayList<>();
        if (StringUtils.isNoneBlank(goodsSearchVo.getGoodsName())) {
            FilterParam filterParam = new FilterParam();
            filterParam.setName("goods_name");
            filterParam.setType("String");
            filterParam.setValue("%" + goodsSearchVo.getGoodsName() + "%");
            filterParam.setCondition("like");
        }
        if (StringUtils.isNoneBlank(goodsSearchVo.getGoodsType())) {
            FilterParam filterParam = new FilterParam();
            filterParam.setName("goods_type");
            filterParam.setType("String");
            filterParam.setValue(goodsSearchVo.getGoodsType());
            filterParam.setCondition("equal");
        }
        if (ObjectUtils.isNotEmpty(goodsSearchVo.getGoodsPriceStart())) {
            FilterParam filterParam = new FilterParam();
            filterParam.setName("goods_price");
            filterParam.setType("BigDecimal");
            filterParam.setValue(goodsSearchVo.getGoodsPriceStart().toString());
            filterParam.setCondition("greater_equal_than");
        }
        if (ObjectUtils.isNotEmpty(goodsSearchVo.getGoodsPriceEnd())) {
            FilterParam filterParam = new FilterParam();
            filterParam.setName("goods_price");
            filterParam.setType("BigDecimal");
            filterParam.setValue(goodsSearchVo.getGoodsPriceEnd().toString());
            filterParam.setCondition("less_equal_than");
        }
        pageParam.setFilter(filterParamList);
        return pageParam;
    }
}
