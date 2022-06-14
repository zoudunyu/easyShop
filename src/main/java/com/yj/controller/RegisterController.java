package com.yj.controller;

import com.yj.model.ResponseData;
import com.yj.model.vo.EncryptionVo;
import com.yj.service.RegisterService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api("注册管理")
@EqualsAndHashCode
@AllArgsConstructor
@RequestMapping("/Register")
public class RegisterController extends BaseController {

    private final RegisterService registerService;

    @PostMapping("In")
    public ResponseData register(@RequestBody @Valid EncryptionVo data) {
        String register = registerService.register(data.getData());
        return render(register);
    }
}
