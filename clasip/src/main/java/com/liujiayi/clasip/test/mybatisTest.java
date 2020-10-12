package com.liujiayi.clasip.test;

import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.pojo.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建时间: 2020-10-12 17:07
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class mybatisTest {

    @Autowired
    StudentDao studentDao;

    //盐
    final static String salt = "&%51273***&&%%$$#@";

    @RequestMapping("/test")
    @ResponseBody
    public Object test1(){
        String kouling="testpwd";//测试口令
        String name="test名字";
        String pwd = DigestUtils.md5DigestAsHex(kouling.getBytes());
        studentDao.insert(new Student(11111,pwd,name,"计算机","计科1801","2018"));
        System.out.println("正在传输...");
        List<Student> studentList=studentDao.selectList(null);
        System.out.println("下面打印传输回的");
        for(Student stu:studentList){
            System.out.println(stu.toString());
        }
        return studentList;
    }
}
