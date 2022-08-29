package com.lili.sds.mapper;

import com.lili.sds.bean.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    // 更适合采用xml映射的方法
    Student selectStudentByIdAndPass(@Param("stuId") String stuId, @Param("stuPass") String stuPass);

    List<Student> selectAllStudent();

     int insertStudent(Student stu);

    int insertStudentHavaPass(Student stu);

    Student selectStudentByStuId(String stuId);

    int deleteStuById(String StuId);

    List<Student> selectStudent(Student stu);

}
