package com.yj.controller;

import com.yj.model.ResponseData;
import com.yj.model.vo.EncryptionVo;
import com.yj.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author zdy
 * @date 2022/06/14
 */
@Api(tags = "登录管理")
@AllArgsConstructor
@RestController
@RequestMapping("/Login")
public class LoginController extends BaseController {

    private final LoginService loginService;

    @ApiOperation("登录 - PC版管理员用户（加密）")
    @PostMapping(value = "/In")
    public ResponseData signIn(@RequestBody @Valid EncryptionVo data) {
        String token = loginService.signIn(data.getData());
        Map<String, String> map = new HashMap<>(1);
        map.put("token", token);
        return render(map);
    }
}
