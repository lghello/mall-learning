package com.example.malllearning.service.impl;

import com.example.malllearning.common.api.CommonResult;
import com.example.malllearning.service.RedisService;
import com.example.malllearning.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String phone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            stringBuilder.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+phone,stringBuilder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+phone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(stringBuilder.toString(),"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String phone, String authCode) {
        if(StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + phone);
        boolean res = authCode.equals(realAuthCode);
        if(res){
            return CommonResult.success(null,"验证码校验正确");
        }
        else{
            return CommonResult.failed("验证码不正确");
        }
    }
}
