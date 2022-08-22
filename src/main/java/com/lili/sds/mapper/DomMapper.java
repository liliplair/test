package com.lili.sds.mapper;

import com.lili.sds.bean.Dom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DomMapper {

    @Select("select* from dom where dom_id=#{domId}")
    Dom selectDomByDomId(String domId);

    @Select("select* from dom")
    List<Dom> selectAllDom();
}
