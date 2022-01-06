package com.example.malllearning.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.example.malllearning.mbg.mapper","com.example.malllearning.dao"})
public class MybatisConfig {
}
