package com.example.malllearning.service;

import com.example.malllearning.common.api.CommonResult;

public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String phone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String phone,String authCode);

}
