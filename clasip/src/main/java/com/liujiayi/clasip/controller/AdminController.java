package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.service.AdminService;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.service.TeacherService;
import com.liujiayi.clasip.util.Constants;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * web端管理员接口
 * @author 刘斯昊
 * @date 2020/10/21
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    /**
     * 获取所有课程
     * @return 课程列表
     */
    @GetMapping("/getAllClass")
    public Object getAllClass(){
        return Result.successs(adminService.getAllClass());
    }

    /**
     * 根据课程号获取所有课程信息，包括任课教师，所有学生
     * @param cid 课程号
     * @return  课程详细信息
     */
    @GetMapping("/getClassInfoByCid/{cid}")
    public Object getClassInfoByCid(@PathVariable("cid") String cid){
        Class classByCid = studentService.getClassByCid(cid);
        if(classByCid == null){
            return Result.failure(ErrorEnum.E_90004);
        }
        Teacher teacherByCid = adminService.getTeacherByCid(cid);
        List<Student> allStudentByCid = studentService.getAllStudentByCid(cid);
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constants.STUDENTS,allStudentByCid);
        result.put(Constants.TEACHER,teacherByCid);
        result.put(Constants.CLASS_INFO,classByCid);

        return Result.successs(result);
    }

    /**
     * 获取所有的教师列表
     * @return 教师列表
     */
    @GetMapping("/getAllTeacher")
    public Object getAllTeacher(){
        return Result.successs(teacherService.getAllTeacher());
    }

    /**
     * 根据cid删除课程
     * @param cid 课程id
     * @return 删除状态
     */
    @GetMapping("/deleteClassByCid/{cid}")
    public Object deleteClassByCid(@PathVariable("cid") String cid){
        int i = adminService.deleteClassByCid(cid);
        return i>0 ? Result.successs("删除成功"):Result.failure(ErrorEnum.E_90004);
    }

    /**
     * 添加学生
     * @param student 学生实体
     * @return 添加状态
     */
    @PostMapping("/addStudent")
    public Object addStudent(@RequestBody String student){
        int i = adminService.addStudent(student);
        return i>0 ? Result.successs("添加成功"):Result.failure(ErrorEnum.E_10009);
    }

    @GetMapping("/deleteStudent/{sid}")
    public Object deleteStudent(@PathVariable("sid") String sid){
        int i = adminService.deleteStudent(sid);
        return i>0 ? Result.successs("删除成功"):Result.failure(ErrorEnum.E_90004);
    }

    @PostMapping("/login")
    public Object login(@RequestBody String token){
        Boolean login = adminService.login(token);
        return login ? Result.successs(Constants.LOGIN_SUCCESS):Result.failure(ErrorEnum.E_401);
    }
}
