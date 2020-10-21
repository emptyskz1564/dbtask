package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Constants;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
@RestController("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 登录方法，post请求，接受前端传来的token
     * @param token   学生账号密码
     * @return 返回登录信息
     */
    @PostMapping("/login")
    public Object login(@RequestBody String token){
        boolean code = studentService.login(token);
        if(code == true){
            return Result.successs(Constants.LOGIN_SUCCESS);
        }else{
            return Result.failure(ErrorEnum.E_401);
        }
    }

    /**
     * 学生端注册方法
     * @param studentInfo 学生信息
     * @return 返回注册信息
     */
    @PostMapping("/register")
    public Object register(@RequestBody String studentInfo){
        boolean code = studentService.register(studentInfo);
        if(code == true){
            return Result.successs(Constants.REGISTER_SUCCESS);
        }else{
            return Result.failure(ErrorEnum.E_10009);
        }
    }


    /**
     * 根据学号获取学生信息
     * @param sid   学号
     * @return  学生信息
     */
    @GetMapping("/getStudentBySid/{sid}")
    public Object getStudentBySid(@PathVariable("sid") String sid){
        Student studentBySid = studentService.getStudentBySid(sid);
        return Result.successs(studentBySid);
    }

    /**
     * 获取某课程的所有学生信息列表
     * @param cid 课程id
     * @return  某课堂的所有学生信息
     */
    @GetMapping("getAllStudentByCid/{cid}")
    public Object getAllStudentByCid(@PathVariable("cid") String cid){
        List<Student> allStudentByCid = studentService.getAllStudentByCid(cid);
        return Result.successs(allStudentByCid);
    }

    /**
     * 加入课程方法
     * @param sid   学生学号
     * @param classCode     课程码
     * @return  加入课程消息
     */
    @GetMapping("/addStudentToClass/{sid}/{classCode}")
    public Object addStudentToClass(@PathVariable("sid") String sid,@PathVariable("classCode") String classCode){
        boolean code = studentService.addStudentToClass(sid, classCode);
        if(code == true){
            return Result.successs("加入课程成功！");
        }else{
            return Result.failure(Constants.SUCCESS_CODE_MSG,"加入课程失败，请检查课程码是否正确！");
        }
    }


}
