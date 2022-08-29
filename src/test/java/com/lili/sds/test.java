package com.lili.sds;

import com.lili.sds.bean.Student;
import com.lili.sds.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class test {

    @Autowired
    private StudentServiceImpl studentService;


    public static void main(String[] args){
        Student student=new Student(null,null,null,null,null,null,null);
        String str = "18836930324 男 202201 王梅";
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
                        student.setStuId("102");
                }
            } else if(s.equals("男")){
                student.setStuSex(0);
            } else if(s.equals("女")){
                student.setStuSex(1);
            } else{
                student.setStuName(s);
            }
        }
        System.out.println(student);
    }
}

