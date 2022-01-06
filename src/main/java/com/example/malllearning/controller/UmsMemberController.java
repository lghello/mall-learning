package com.example.malllearning.controller;

import com.example.malllearning.common.api.CommonResult;
import com.example.malllearning.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "UmsMemberController",description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String phone){
        return memberService.generateAuthCode(phone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/verifyAuthCode")
    public CommonResult verifyAuthCode(@RequestParam String phone,
                                       @RequestParam String authCode){
        return memberService.verifyAuthCode(phone,authCode);
    }

    @ApiOperation("测试参数")
    @GetMapping
    public CommonResult testPara(String idea){
        return CommonResult.success(idea);
    }
}
