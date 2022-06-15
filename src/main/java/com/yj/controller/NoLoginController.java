package com.yj.controller;

import com.yj.dao.master.service.GoodsService;
import com.yj.model.ResponseData;
import com.yj.model.vo.GoodsSearchVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("NoLogin")
@Api(tags = "普通控制器，不需要登录即可访问")
public class NoLoginController extends BaseController {

    private final GoodsService goodsService;

    @PostMapping("Goods/Page")
    public ResponseData goodsPage(GoodsSearchVo goodsSearchVo) {
        return render(goodsService.listPage(goodsSearchVo));
    }

    @GetMapping("Goods")
    public ResponseData getGoodsById(@RequestParam Integer goodsId) {
        return render(goodsService.getById(goodsId));
    }
}
