package com.example.malllearning.dao;

import com.example.malllearning.mbg.model.PmsBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandDao {
    @Select("select * from pms_brand where id > #{id}")
    public List<PmsBrand> getListById(long id);
    public PmsBrand getPmsBrand(Long id);
}
