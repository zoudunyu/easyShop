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
@RequestMapping("YesLogin")
@Api(tags = "用户控制器，需要登录才能访问")
public class YesLoginController extends BaseController {


}
