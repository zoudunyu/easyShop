package com.yj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yj.dao.master.entity.ShoppingCart;
import com.yj.dao.master.service.GoodsService;
import com.yj.dao.master.service.ShoppingCartService;
import com.yj.dao.master.service.UserInfoService;
import com.yj.model.vo.AddShoppingCartVo;
import com.yj.model.vo.DeleteShoppingCartVo;
import com.yj.model.vo.EditShoppingCartVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ShoppingCarService {

    private final GoodsService goodsService;

    private final UserInfoService userInfoService;

    private final ShoppingCartService shoppingCartService;

    public Boolean saveCart(AddShoppingCartVo addShoppingCartVo) {
        goodsService.validation(addShoppingCartVo.getGoodsId());
        userInfoService.validation(addShoppingCartVo.getUserId());
        ShoppingCart shoppingCart = shoppingCartService.voToBean(addShoppingCartVo);
        return shoppingCartService.save(shoppingCart);
    }

    public Boolean delete(DeleteShoppingCartVo deleteShoppingCartVo) {
        return shoppingCartService.delete(deleteShoppingCartVo);
    }

    public Boolean modify(EditShoppingCartVo editShoppingCartVo) {
        shoppingCartService.validation(editShoppingCartVo.getCartId());
        ShoppingCart shoppingCart = shoppingCartService.voToBean(editShoppingCartVo);
        return shoppingCartService.updateById(shoppingCart);
    }

    public List<ShoppingCart> findByUserId(Integer userId) {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("user_id", userId);
        return shoppingCartService.list(queryWrapper);
    }
}
