package com.lili.sds.controller;

import com.lili.sds.bean.MyError;
import com.lili.sds.bean.Repair;
import com.lili.sds.bean.Student;
import com.lili.sds.service.RepairService;
import com.lili.sds.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RepairService repairService;

    @PostMapping(value = "/stu/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String,Object> map, HttpSession session) {
        Student student=studentService.login(username,password);
        if(student!=null){
            session.setAttribute("loginUser",username);
            return "redirect:/stumain.html";
        } else{
            map.put("msg","用户名或密码错误");
            return  "login";
        }
    }

    //返回首页
    @GetMapping(value = "/stu/toindex")
    public String toindex(HttpSession httpSession, Model model){
        Student stu= studentService.selectById((String) httpSession.getAttribute("loginUser"));
        return "redirect:/stumain.html";
    }

    //返回学生信息修改页面
    @GetMapping(value = "/stu/toUpdateMsgPage")
    public String toUpdateMsgPage(HttpSession httpSession, Model model){
        Student stu= studentService.selectById((String) httpSession.getAttribute("loginUser"));
        model.addAttribute("stu",stu);
        return "stu/updateStu";
    }
    //更新学生信息操作
    @PutMapping(value = "/stu/updateStuMsg")
    public String updateStuMsg(@Valid Student stu, BindingResult bindingResult, Model model,HttpSession httpSession) {
        Student student= studentService.selectById((String) httpSession.getAttribute("loginUser"));
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if(allErrors.size()==0){
            stu.setStuId(student.getStuId());
            if(stu.getStuPass()=="" || stu.getStuPass()==null) {
                stu.setStuPass("123");
            }
            stu.setStuName(student.getStuName());
            stu.setStuSex(student.getStuSex());
            stu.setStuDom(student.getStuDom());
            stu.setStuClass("");
            studentService.deleStu(student.getStuId());
            studentService.addStudentHavePass(stu);
            return "redirect:/stumain.html";
        }else{
            for (ObjectError error:allErrors){
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("stu",student);
            return "stu/updateStu";
        }
    }

    //查看报修信息
    //新增报修信息
    @GetMapping(value = "/stu/toresdmin")
    public String toresdmin(HttpSession httpSession, Model model){
        Student stu= studentService.selectById((String) httpSession.getAttribute("loginUser"));
        List<Repair> repairs = repairService.getRepairByDom(stu.getStuDom());
        model.addAttribute("repairs", repairs);
        return "/stu/repairlist";
    }
    // 返回报修添加
    @GetMapping(value="/stu/repairadd")
    public String repairtoaddpage() { return "stu/addrepair"; }
    // 处理事务
    @PostMapping(value="/stu/repairAdd")
    public String repairAdd(@Valid Repair repairs,HttpSession httpSession, BindingResult bindingResult, Model model){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg= new ArrayList<>();
        Student student= studentService.selectById((String) httpSession.getAttribute("loginUser"));
        if(allErrors.size()==0)
        {
            if(repairService.getRepairById(repairs.getRepairId())==null){
                repairs.setRepairDom(student.getStuDom());
                repairs.setRepairResult("维修中");
                repairService.addRepair(repairs);
                return "redirect:/stu/toresdmin";
            }else{
                errmsg.add(new MyError("已存在相同的维修编号"));
                model.addAttribute("errors", errmsg);
                model.addAttribute("repair", repairs);
                return "stu/addrepair";
            }
        }else {
            for (ObjectError error:allErrors){
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("repair",repairs);
            return "stu/addrepair";
        }
    }

}
