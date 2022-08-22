package com.lili.sds.service.impl;

import com.lili.sds.bean.Student;
import com.lili.sds.mapper.StudentMapper;
import com.lili.sds.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Deacription TODO
 * @Author daier
 * @Date 2021/1/4 23:51
 * @Version 1.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student login(String stuId, String stuPass)
    {
        return studentMapper.selectStudentByIdAndPass(stuId,stuPass);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.selectAllStudent();
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public Student selectById(String stuId) {
        return studentMapper.selectStudentByStuId(stuId);
    }

    @Override
    public int deleStu(String stuId) {
        return studentMapper.deleteStuById(stuId);
    }

    @Override
    public int addStudentHavePass(Student student) {
        return studentMapper.insertStudentHavaPass(student);
    }
}
