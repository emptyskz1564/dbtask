package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘斯昊
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherDao teacherDao;


    @ResponseBody
    @GetMapping("teacherlogin/{tid}/{pwd}")
    public Object teaLogin(@PathVariable("tid")String tid,@PathVariable("pwd")String pwd){
        Teacher teacher = teacherDao.tealogin(tid,pwd);
        if(teacher.getTid().equals(tid)){
            return Result.successs2("登录成功！");
        }else {
            return Result.failure(ErrorEnum.E_401);
        }

    }


    @ResponseBody
    @GetMapping("search/{info}")
    public Object sear(@PathVariable("info")String info){
        info = "%"+info+"%";
        List<Teacher> teachers = teacherDao.search(info);
        if(teachers.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else {
            return Result.successs2(teachers);
        }
    }



}