package com.lili.sds.mapper;

import com.lili.sds.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student where  stu_id=#{stuId} and stu_pass=#{stuPass}")
    Student selectStudentByIdAndPass(@Param("stuId") String stuId, @Param("stuPass") String stuPass);

    @Select("select * from student")
    List<Student> selectAllStudent();

    @Insert("insert into student (stu_id,stu_name,stu_pass,stu_sex,stu_tele,stu_dom) values" +
            "(#{stuId},#{stuName},#{stuPass},#{stuSex},#{stuTele},#{stuDom})")
     int insertStudent(Student stu);

    @Insert("insert into student (stu_id,stu_name,stu_pass,stu_sex,stu_tele,stu_dom) values" +
            "(#{stuId},#{stuName},#{stuPass},#{stuSex},#{stuTele},#{stuDom})")
    int insertStudentHavaPass(Student stu);

    @Select("select * from student where  stu_id=#{stuId} ")
    Student selectStudentByStuId(String stuId);

    @Delete("DELETE from student where stu_id=#{stuId}")
    int deleteStuById(String StuId);
}
