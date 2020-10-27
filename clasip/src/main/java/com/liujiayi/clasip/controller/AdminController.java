package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.service.AdminService;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * web端管理员接口
 * @author 刘斯昊
 * @date 2020/10/21
 */
@RestController("/admin")
public class AdminController {

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    /**
     * 获取所有课程
     * @return 课程列表
     */
    @GetMapping("/getAllClass")
    public Object getAllClass(){
        return Result.successs(adminService.getAllClass());
    }

    @GetMapping("/getClassInfoByCid/{cid}")
    public Object getClassInfoByCid(@PathVariable("cid") String cid){
        List<Student> allStudentByCid = studentService.getAllStudentByCid(cid);
        Class classByCid = studentService.getClassByCid(cid);


        return Result.successs(studentService.getClassByCid(cid));
    }









}
