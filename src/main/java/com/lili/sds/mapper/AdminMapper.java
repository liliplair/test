package com.lili.sds.mapper;

import com.lili.sds.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface AdminMapper {

    /**
     * 登录时查找账户\密码, 查看是否在系统内有该用户
     * @param adminId
     * @param adminPass
     * @return 查找到的Admin对象或者null
     */
    @Select("select * from admin where  admin_id=#{adminId} and admin_pass=#{adminPass}")
    Admin selectAdminByIdAndPass(@Param("adminId") String adminId, @Param("adminPass") String adminPass);

}
