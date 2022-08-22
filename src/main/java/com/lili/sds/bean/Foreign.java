package com.lili.sds.bean;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Foreign {
    @Size(min=4,max = 4,message = "访客ID长度必须为4")
    private String foreignId;
    @Size(min=1,max = 10,message = "名字长度必须在1-10之间")
    private String foreignName;
    private String foreignSex;
    @Size(min = 11,max = 11,message = "请输入11位手机号码")
    private String foreignTele;

    private String foreignDate;
    private String foreignText;
}
