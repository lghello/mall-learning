package com.example.malllearning.controller;
import com.example.malllearning.common.api.CommonPage;
import com.example.malllearning.common.api.CommonResult;
import com.example.malllearning.mbg.model.PmsBrand;
import com.example.malllearning.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌管理Controller
 * Created by macro on 2019/4/19.
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@Controller
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }
}

//import com.example.malllearning.common.api.CommonPage;
//import com.example.malllearning.common.api.CommonResult;
//import com.example.malllearning.mbg.mapper.PmsBrandMapper;
//import com.example.malllearning.mbg.model.PmsBrand;
//import com.example.malllearning.mbg.model.PmsBrandExample;
//import com.example.malllearning.service.PmsBrandService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Api(tags = "PmsBrandController",description = "商品品牌管理")
//@RestController
//@RequestMapping("/brand")
//public class PmsBrandController {
//
//    @Autowired
//    private PmsBrandService brandService;
//
//    public static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
//
//    @ApiOperation("获取所有品牌列表")
//    @GetMapping("/listAll")
//    public CommonResult<List<PmsBrand>> getBrandList(){
//        return CommonResult.success(brandService.listAllBrand());
//    }
//
//    @ApiOperation("添加品牌")
//    @PostMapping("/create")
//    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
//        CommonResult result;
//        int count = brandService.createBrand(pmsBrand);
//        if(count==1){
//            result = CommonResult.success(pmsBrand);
//            LOGGER.debug("createBrand success:{}", pmsBrand);
//        }
//        else{
//            result = CommonResult.failed("操作失败");
//            LOGGER.debug("createBrand failed:{}", pmsBrand);
//        }
//        return result;
//    }
//
//    @ApiOperation("删除指定id的品牌")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult deleteBrand(@PathVariable("id") Long id) {
//        int count = brandService.deleteBrand(id);
//        if (count == 1) {
//            LOGGER.debug("deleteBrand success :id={}", id);
//            return CommonResult.success(null);
//        } else {
//            LOGGER.debug("deleteBrand failed :id={}", id);
//            return CommonResult.failed("操作失败");
//        }
//    }
//
//    @ApiOperation("更新指定id品牌信息")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
//        CommonResult commonResult;
//        int count = brandService.updateBrand(id, pmsBrandDto);
//        if (count == 1) {
//            commonResult = CommonResult.success(pmsBrandDto);
//            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
//        } else {
//            commonResult = CommonResult.failed("操作失败");
//            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
//        }
//        return commonResult;
//    }
//
//    @ApiOperation("分页查询品牌列表")
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
//                                                        @ApiParam("页码") Integer pageNum,
//                                                        @RequestParam(value = "pageSize", defaultValue = "3")
//                                                        @ApiParam("每页数量") Integer pageSize) {
//        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
//        return CommonResult.success(CommonPage.restPage(brandList));
//    }
//
//
//    @ApiOperation("获取指定id的品牌详情")
//    @GetMapping("/getById/{id}")
//    public CommonResult<PmsBrand> getById(@PathVariable Long id){
//        PmsBrand brand = brandService.getBrand(id);
//        return CommonResult.success(brand);
//    }
//
//
////    @GetMapping("/mapper/getList")
////    public List<PmsBrand> getList(){
////        //这样的话每次查询只能查到3个数据
////        PageHelper.startPage(1, 3);
////        List<PmsBrand> list = mapper.selectByExample(new PmsBrandExample());
////        //通过构造PageInfo对象获取分页信息，如当前页码，总页数，总条数
////        PageInfo<PmsBrand> pageInfo = new PageInfo<PmsBrand>(list);
////        System.out.println("======================");
//////        System.out.println(pageInfo);
//////        System.out.println("========================");
//////        System.out.println(pageInfo.getList());
//////        System.out.println("========================");
//////        System.out.println(pageInfo.getPages());
//////        System.out.println("=========================");
//////        System.out.println(pageInfo.getPageNum());
//////        System.out.println("=========================");
//////        List<PmsBrand> list1 = pageInfo.getList();
//////        System.out.println(list1);
////        return list;
////    }
//}


