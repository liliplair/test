package com.lili.sds.mapper;

import com.lili.sds.bean.Foreign;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface ForeignMapper {

    @Select("select * from fore")
    List<Foreign> selectAllForeign();

    @Insert("insert into fore (foreign_id,foreign_name,foreign_sex,foreign_tele,foreign_date,foreign_text) values(#{foreignId},#{foreignName},#{foreignSex},#{foreignTele},#{foreignDate},#{foreignText})")
    int insertForeign(Foreign foreign);

    @Delete("DELETE from fore where foreign_id=#{foreignId}")
    int deleteForeignById(String foreignId);

    @Select("select * from fore where foreign_id=#{foreignId}")
    Foreign selectForeignById(String foreignId);
}
