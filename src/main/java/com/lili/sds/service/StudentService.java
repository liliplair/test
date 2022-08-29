package com.lili.sds.service;

import com.lili.sds.bean.Student;

import java.util.List;

public interface StudentService {
     Student stuLogin(String stuId, String stuPass);

     List<Student> getAllStudent();

     int addStudent(Student student);

     int addStudentHavePass(Student student);

     /**
      * 提供模糊查询功能,
      * 输入 2018年: 查询2018开头的所有学生
      * 输入 2班: 查询[-3:-4]==2的学生
      * 输入 两位数: 查询后两位对应学生
      * 输入其他: 利用通配符匹配
      * @param stuId
      * @return
      */
     Student selectById(String stuId);

     int deleStu(String stuId);

     List<Student> selectStudent(String str);
}
