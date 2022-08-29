package com.lili.sds.bean;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Student {
    @Size(min=14,max = 14,message = "学号长度必须为14")
    private String stuId;
    @Size(min=1,max = 10,message = "名字长度必须在1-10之间")
    private String stuName;
    private String stuPass;
    private String stuClass;
    private Integer stuSex;
    @Size(min = 11,max = 11,message = "请输入11位手机号码")
    private String stuTele;
    private String stuDom;

    public Student(String stuId, String stuName, String stuPass, String stuClass, Integer stuSex, String stuTele, String stuDom) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuPass = stuPass;
        this.stuClass = stuClass;
        this.stuSex = stuSex;
        this.stuTele = stuTele;
        this.stuDom = stuDom;
    }

    public Student() {}
}
