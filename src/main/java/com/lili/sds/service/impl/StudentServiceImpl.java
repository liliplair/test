package com.lili.sds.service.impl;

import com.lili.sds.bean.Student;
import com.lili.sds.mapper.StudentMapper;
import com.lili.sds.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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
    public Student stuLogin(String stuId, String stuPass)
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

    @Override
    public List<Student> selectStudent(String str) {
        if(str=="" || str==null){
            return getAllStudent();
        }
        Student student=new Student(null,null,null,null,null,null,null);
        // 从String中得到Student的属性
        String[] arr = str.split("\\s+");
        Pattern isNum = Pattern.compile("[0-9]*");
        for(String s: arr){
            System.out.println(s);
            if(isNum.matcher(s).matches()){
                if(s.length()=="19936583458".length()){
                    student.setStuTele(s);
                } else if(s.length()=="20181685310268".length()){
                    student.setStuId(s);
                } else if(s.length()=="202201".length()){
                    student.setStuDom(s);
                } else {
                    if(student.getStuId()==null)
                        student.setStuId(s);
                }
            } else if(s.equals("男")){
                student.setStuSex(1);
            } else if(s.equals("女")){
                student.setStuSex(0);
            } else{
                student.setStuName(s);
            }
        }
//        System.out.println(student);
        return studentMapper.selectStudent(student);
    }
}
