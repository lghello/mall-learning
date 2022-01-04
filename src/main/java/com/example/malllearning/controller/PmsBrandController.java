package com.example.malllearning.controller;

import com.example.malllearning.common.api.CommonResult;
import com.example.malllearning.mbg.model.PmsBrand;
import com.example.malllearning.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    public static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(brandService.listAllBrand());
    }

//    @PostMapping("/create")
//    public CommonResult createBrand()

}
