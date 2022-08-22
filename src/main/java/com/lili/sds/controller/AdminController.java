package com.lili.sds.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lili.sds.bean.*;
import com.lili.sds.service.AdminService;
import com.lili.sds.service.ForeignService;
import com.lili.sds.service.RepairService;
import com.lili.sds.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* 控制器
* 把用户提交的请求通过对URL的匹配分配给不同的接收器，再进行处理，然后用户返回结果
* @Controller 告知Spring这是一个控制器
* @RequestMapping 告知该函数需要相应的URL 包含若干参数，也允许缺省
* 返回的字符串代表相应名称的模板
* @RequestMapping 可以获取类似表单传回的信息形式 如果限定Get请求，使用GetMapping代替
*   此时可以直接用javaBean来接收参数，前提就是属性名和参数名相同，同时要有get、set方法
*
* @GetMapping @PostMapping @PutMapping @DeleteMapping 对应数据库信息的查询 新增 修改 删除操作
* */

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ForeignService foreignService;


    @PostMapping(value = "/adm/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Map<String, Object> map, HttpSession session) {
        Admin adm = adminService.adminLogin(username, password);
        if (adm != null) {
            session.setAttribute("loginUser", username);
            return "redirect:/admmain.html";
        } else {
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }

    //返回首页
    @GetMapping(value = "/adm/toindex")
    public String toindex() {
        return "redirect:/admmain.html";
    }

    //返回学生管理首页
    /*
     * para      : pathvariable pagenumber
     * return    : stulist.html
     * */
    @GetMapping(value = "/adm/tostudmin/{pn}")
    public String tostudmin(@PathVariable("pn") Integer pn, Model model) {
        PageHelper.startPage(pn, 6);
        List<Student> students = studentService.getAllStudent();
        PageInfo<Student> page = new PageInfo<Student>(students, 5);
        model.addAttribute("pageInfo", page);
        return "forward:/stuadmin.html";
    }

    //返回学生添加页面
    /*
     * para      :
     * return    : addstu.html
     * */
    @GetMapping(value = "/adm/stuadd")
    public String stutoaddpage(Model model) {
        return "adm/addstu";
    }

    //处理学生添加事务
    /*
     * para      :
     * return    :
     *  成功返回stulist.html 中的第一页
     *  失败返回 addstu.html
     * */
    @PostMapping(value = "/adm/stuAdd")
    public String stuAdd(@Valid Student student, BindingResult bindingResult, Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if (allErrors.size() == 0) {
            Student studentVail = studentService.selectById(student.getStuId());
            if (studentVail == null) {
                student.setStuPass("123");
                studentService.addStudent(student);
                return "redirect:/adm/tostudmin/1";
            } else {
                errmsg.add(new MyError("已存在该学号的学生"));
                model.addAttribute("errors", errmsg);
                model.addAttribute("stu", student);
                return "adm/addstu";
            }
        } else {
            for (ObjectError error : allErrors) {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors", errmsg);
            model.addAttribute("stu", student);
            return "adm/addstu";
        }
    }

    //处理删除学生事务
    @DeleteMapping(value = "/adm/stu/{stuId}")
    public String delestu(@PathVariable("stuId") String stuId) {
        studentService.deleStu(stuId);
        return "redirect:/adm/tostudmin/1";
    }

    //返回学生修改页面
    @GetMapping(value = "/adm/stu/{stuId}")
    public String updateStuPage(@PathVariable("stuId") String stuId, Model model) {
        Student stu = studentService.selectById(stuId);
        model.addAttribute("stu", stu);
        return "adm/updatestu";
    }

    //更新学生信息操作
    @PutMapping(value = "/adm/stu")
    public String updateStu(@Valid Student student, BindingResult bindingResult, Model model) {


        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if (allErrors.size() == 0) {
            System.out.println(student);
            studentService.deleStu(student.getStuId());
            studentService.addStudentHavePass(student);
            return "redirect:/adm/tostudmin/1";
        } else {
            for (ObjectError error : allErrors) {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors", errmsg);
            model.addAttribute("stu", student);
            return "adm/updatestu";
        }
    }

    //根据ID查询学生
    @GetMapping(value = "/adm/selectById")
    public String selectById(@Param("stuId") String stuId, Model model) {
        Student student = studentService.selectById(stuId);
        model.addAttribute("stus", student);
        return "adm/stubyid";
    }


    //返回访客管理首页
    @GetMapping(value = "/adm/toforeigndmin/{pn}")
    public String toforeignadmin(@PathVariable("pn") Integer pn, Model model) {
        PageHelper.startPage(pn, 6);
        List<Foreign> foreigns = foreignService.getAllForeign();
        PageInfo<Foreign> page = new PageInfo<Foreign>(foreigns, 5);
        model.addAttribute("pageInfo", page);
        return "adm/foreignlist";
    }
    //返回访客添加页面
    @GetMapping(value = "/adm/foreignadd")
    public String foreigntoaddpage() {
        return "adm/addforeign";
    }
    //处理访客添加事务
    @PostMapping(value = "/adm/foreignAdd")
    public String foreignAdd(@Valid Foreign foreign, BindingResult bindingResult, Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if (allErrors.size() == 0) {
            Foreign foreignVail = foreignService.selectById(foreign.getForeignId());
            if (foreignVail == null) {
                foreignService.addForeign(foreign);
                return "redirect:/adm/toforeigndmin/1";
            } else {
                errmsg.add(new MyError("已存在该编号的访客"));
                model.addAttribute("errors", errmsg);
                model.addAttribute("foreign", foreign);
                return "addforeign";
            }
        } else {
            for (ObjectError error : allErrors) {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors", errmsg);
            model.addAttribute("foreign", foreign);
            return "addforeign";
        }
    }
    //返回访客修改页面
    @GetMapping(value = "/adm/foreign/{foreignId}")
    public String updateForeignPage(@PathVariable("foreignId") String foreignId, Model model) {
        Foreign foreign = foreignService.selectById(foreignId);
        model.addAttribute("foreign", foreign);
        return "adm/updateforeign";
    }
    //更新访客信息操作
    @PutMapping(value = "/adm/foreign")
    public String updateForeign(@Valid Foreign foreign, BindingResult bindingResult, Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if (allErrors.size() == 0) {
            System.out.println(foreign);
            foreignService.deleteForeign(foreign.getForeignId());
            foreignService.addForeign(foreign);
            return "redirect:/adm/toforeigndmin/1";
        } else {
            for (ObjectError error : allErrors) {
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors", errmsg);
            model.addAttribute("foreign", foreign);
            return "adm/upadteforeign";
        }
    }
    //处理删除访客事务
    @DeleteMapping(value = "/adm/foreign/{foreignId}")
    public String deleforeign(@PathVariable("foreignId") String foreignId) {
        foreignService.deleteForeign(foreignId);
        return "redirect:/adm/toforeigndmin/1";
    }


    // 报修
    @GetMapping(value="/adm/torepairdmin/{pn}")
    public String torepairadmin(@PathVariable("pn") Integer pn, Model model){
        PageHelper.startPage(pn, 6);
        List<Repair> repairs = repairService.getAllRepair();
        PageInfo<Repair> page = new PageInfo<Repair>(repairs, 5);
        model.addAttribute("pageInfo", page);
        return "adm/repairlist";
    }
    // 返回报修添加
    @GetMapping(value="/adm/repairadd")
    public String repairtoaddpage() { return "adm/addrepair"; }
    // 处理事务
    @PostMapping(value="/adm/repairAdd")
    public String repairAdd(@Valid Repair repairs, BindingResult bindingResult, Model model){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg= new ArrayList<>();
        if(allErrors.size()==0)
        {
            if(repairService.getRepairById(repairs.getRepairId())==null){
                repairService.addRepair(repairs);
                return "redirect:/adm/torepairdmin/1";
            }else{
                errmsg.add(new MyError("已存在相同的维修编号"));
                model.addAttribute("errors", errmsg);
                model.addAttribute("repair", repairs);
                return "adm/addrepair";
            }
        }else {
            for (ObjectError error:allErrors){
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors",errmsg);
            model.addAttribute("repair",repairs);
            return "adm/addrepair";
        }
    }
    //处理删除报修事务
    @DeleteMapping(value = "/adm/repair/{repairId}")
    public String delerepair(@PathVariable("repairId") String repairId) {
        repairService.deleteById(repairId);
        return "redirect:/adm/torepairdmin/1";
    }
    // 返回修改页面
    @GetMapping(value="/adm/repair/{repairId}")
    public String updateRepairPage(@PathVariable("repairId") String repairId, Model model){
        Repair repair = repairService.getRepairById(repairId);
        model.addAttribute("repair", repair);
        return "adm/updaterepair";
    }
    // 更新
    //@Valid和BindingResult配套使用，@Valid用在参数前，BindingResult作为校验结果绑定返回
    @PutMapping(value="/adm/repair")
    public String updateRepair(@Valid Repair repair, BindingResult bindingResult, Model model){
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<MyError> errmsg = new ArrayList<>();
        if(allErrors.size()==0){
            System.out.println(repair);
            repairService.deleteById(repair.getRepairId());
            repairService.addRepair(repair);
            return "redirect:/adm/torepairdmin/1";
        } else{
            for(ObjectError error: allErrors){
                errmsg.add(new MyError(error.getDefaultMessage()));
            }
            model.addAttribute("errors", errmsg);
            model.addAttribute("repair", repair);
            return "adm/updaterepair";
        }

    }

}