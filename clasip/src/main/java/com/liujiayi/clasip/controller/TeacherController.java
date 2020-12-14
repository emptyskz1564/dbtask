package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.OpenClassDao;
import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.pojo.association.OpenClass;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Random;

/**
 * @author 刘斯昊
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    OpenClassDao openClassDao;


    @ResponseBody
    @GetMapping("teacherlogin/{tid}/{pwd}")
    public Object teaLogin(@PathVariable("tid")String tid,@PathVariable("pwd")String pwd){
        List<Teacher> teachers = teacherDao.tealogin(tid,pwd);
        if(teachers.size()==1){
            return Result.successs("登录成功！");
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


    @ResponseBody
    @GetMapping("/sign/{tid}/{cid}")
    public Object sign(@PathVariable("tid")String tid,@PathVariable("cid")String cid){
        Random rand = new Random();
        //生成签到码
        int version =rand.nextInt(999999 - 100000 + 1) + 100000;
        LocalDateTime localDateTime = LocalDateTime.now();
        OpenClass openClass = new OpenClass(tid,cid,version,localDateTime);
        openClassDao.insert(openClass);
        return Result.successs(String.valueOf(version));
    }





}