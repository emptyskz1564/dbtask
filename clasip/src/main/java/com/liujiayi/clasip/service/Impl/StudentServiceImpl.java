package com.liujiayi.clasip.service.Impl;

import com.alibaba.fastjson.JSON;
import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.Token;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired StudentDao studentDao;
    @Autowired ClassDao classDao;

    @Override
    public List<Student> getAllStudentByCid(String cid) {

        return null;
    }

    @Override
    public Student getStudentBySid(String sid) {
        return studentDao.selectById(sid);
    }

    @Override
    public boolean login(String token) {
        //缺少参数验证
        Token StudentToken = JSON.parseObject(token, Token.class);
        HashMap<String, Object> condition = new HashMap<>();
        condition.put(Constants.STUDENT_ID,StudentToken.getAccount());

        List<Student> students = studentDao.selectByMap(condition);

        return students.get(0).getPwd() == StudentToken.getPassword();
    }

    @Override
    public boolean addStudentToClass(String sid, String classCode) {

        return false;
    }

    @Override
    public boolean register(String studentInfo) {
        //缺少参数验证
        Student student = JSON.parseObject(studentInfo, Student.class);
        int insert = studentDao.insert(student);
        return insert>0;
    }

    @Override
    public List<Class> getAllClassBySid(String sid) {

        return null;
    }

    @Override
    public Class getClassByCid(String cid) {
        return null;
    }
}

