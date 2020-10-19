package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
@RestController("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/getStudentBySid/{sid}")
    public Object getStudentBySid(@PathVariable("sid") String sid){
        Student studentBySid = studentService.getStudentBySid(sid);
        return Result.successs(studentBySid);
    }

    @PostMapping("getAllStudentByCid/{cid}")
    public Object getAllStudentByCid(@PathVariable("cid") String cid){
        List<Student> allStudentByCid = studentService.getAllStudentByCid(cid);
        return Result.successs(allStudentByCid);
    }


}
