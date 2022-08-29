package com.lili.sds;

import com.lili.sds.bean.Repair;
import com.lili.sds.controller.StudentController;
import com.lili.sds.mapper.RepairMapper;
import com.lili.sds.service.RepairService;
import com.lili.sds.service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MyApplicationtest {

    private Repair repair;

//    @Autowired
//    private RepairMapper repairMapper;

    @Autowired
    private StudentController studentController;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private RepairService repairService;
//
//    @Test
//    public void repairTest (){
//        repair = new Repair("202219","测试","2022-08-19","完成","测试项","202208");
//        repairService.addRepair(repair);
//    }

    @Test
    public void selectStudentTest(){
        System.out.println(studentService.selectStudent("20181685310271"));
    }

}
