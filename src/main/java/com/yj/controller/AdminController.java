package com.yj.controller;

import com.yj.dao.master.service.GoodsService;
import com.yj.model.ResponseData;
import com.yj.model.vo.AddGoodsVo;
import com.yj.model.vo.EditGoodsVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("NoLogin")
@Api(tags = "管理员控制器，只有管理员可以访问")
public class AdminController extends BaseController {

    private final GoodsService goodsService;

    @PostMapping("Goods")
    public ResponseData save(@RequestBody @Valid AddGoodsVo addGoodsVo) {
        return render(goodsService.save(addGoodsVo));
    }

    @PutMapping("Goods")
    public ResponseData modify(@RequestBody @Valid EditGoodsVo editGoodsVo) {
        return render(goodsService.modify(editGoodsVo));
    }

    @DeleteMapping("Good")
    public ResponseData delete(@RequestParam Integer goodsId) {
        return render(goodsService.delete(goodsId));
    }
}
