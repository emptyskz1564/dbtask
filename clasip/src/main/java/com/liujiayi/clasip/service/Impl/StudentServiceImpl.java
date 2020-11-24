package com.liujiayi.clasip.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.ClassStudentDao;
import com.liujiayi.clasip.dao.SignUpDao;
import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.SignUp;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.Token;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    @Autowired ClassStudentDao classStudentDao;
    @Autowired SignUpDao signUpDao;

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
        Token studentToken = JSON.parseObject(token, Token.class);
        HashMap<String, Object> condition = new HashMap<>();
        condition.put(Constants.STUDENT_ID,studentToken.getAccount());

        List<Student> students = studentDao.selectByMap(condition);

        MD5 md5 = new MD5();

        return studentToken.getPassword().equals(students.get(0).getPwd());
    }

    @Override
    public boolean addStudentToClass(String sid, String cid) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put(Constants.CLASS_ID,cid);
        List<Class> classes = classDao.selectByMap(conditions);
        int i = classStudentDao.insert(new ClassStudent(classes.get(0).getCid(), sid));
        return i>0;
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
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put(Constants.STUDENT_ID,sid);
        List<ClassStudent> classStudents = classStudentDao.selectByMap(conditions);
        ArrayList<String> cids = new ArrayList<>();
        for(ClassStudent classStudent : classStudents){
            cids.add(classStudent.getCid());
        }
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Constants.CLASS_ID,cids);
        List<Class> classes = classDao.selectList(queryWrapper);
        return classes;
    }

    @Override
    public Class getClassByCid(String cid) {
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        classQueryWrapper.eq(Constants.CLASS_ID,cid);
        Class aClass = classDao.selectOne(classQueryWrapper);
        return aClass;
    }

    @Override
    public boolean signUp(SignUp signUp) {
        int insert = signUpDao.insert(signUp);
        return insert>0;
    }
}

