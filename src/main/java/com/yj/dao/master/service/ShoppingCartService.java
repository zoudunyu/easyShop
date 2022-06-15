package com.yj.dao.master.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.dao.BaseDaoServiceImpl;
import com.yj.dao.master.entity.ShoppingCart;
import com.yj.dao.master.mapper.ShoppingCartMapper;
import com.yj.exception.MyException;
import com.yj.model.vo.AddShoppingCartVo;
import com.yj.model.vo.DeleteShoppingCartVo;
import com.yj.model.vo.EditShoppingCartVo;
import com.yj.util.BeanUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 购物车表 服务实现类
 *
 * @author 邹敦宇
 * @since 2022-06-13 19:25:21
 */
@DS("master")
@Service
public class ShoppingCartService extends BaseDaoServiceImpl<ShoppingCartMapper, ShoppingCart> {

    public ShoppingCart validation(Integer cartId) {
        if (cartId == null) {
            return null;
        }
        ShoppingCart shoppingCart = getById(cartId);
        if (ObjectUtils.isEmpty(shoppingCart)) {
            throw new MyException("购物车不存在", "购物车异常", HttpStatus.NOT_FOUND);
        }
        return shoppingCart;
    }

    public Boolean delete(DeleteShoppingCartVo deleteShoppingCartVo) {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("user_id", deleteShoppingCartVo.getUserId());
        queryWrapper.ge("goods_id", deleteShoppingCartVo.getGoodsId());
        return remove(queryWrapper);
    }

    public ShoppingCart voToBean(AddShoppingCartVo addShoppingCartVo) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(addShoppingCartVo.getUserId());
        shoppingCart.setGoodsId(addShoppingCartVo.getGoodsId());
        shoppingCart.setCreateTime(System.currentTimeMillis());
        shoppingCart.setGoodsNum(addShoppingCartVo.getGoodsNum());
        return shoppingCart;
    }

    public ShoppingCart voToBean(EditShoppingCartVo editShoppingCartVo) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtil.copyNullProperties(editShoppingCartVo, shoppingCart);
        return shoppingCart;
    }
}
