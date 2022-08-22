package com.lili.sds.mapper;

import com.lili.sds.bean.Repair;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RepairMapper {

    @Select("select * from repair")
    List<Repair> selectAllRepair();

    @Delete("DELETE from repair where repair_id=#{repairId}")
    int deleteRepairById(String repairId);

    @Insert("insert into repair (repair_id, repair_name, repair_date, repair_result, repair_overview, repair_dom) " +
            "values(#{repairId},#{repairName},#{repairDate},#{repairResult},#{repairOverview},#{repairDom})")
    int insertRepair(Repair repair);

    @Select("select * from repair where repair_id=#{repairId}")
    Repair selectRepairById(String repairId);

    @Select("select * from repair where repair_dom=#{repairDom}")
    List<Repair> selectRepairByDom(String repairDom);
}
