package com.liujiayi.clasip.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.ClassTeacherDao;
import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.pojo.Token;
import com.liujiayi.clasip.pojo.association.ClassTeacher;
import com.liujiayi.clasip.service.AdminService;
import com.liujiayi.clasip.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘斯昊
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ClassDao classDao;

    @Autowired
    ClassTeacherDao classTeacherDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Class> getAllClass() {
        return classDao.selectList(null);
    }

    @Override
    public Teacher getTeacherByCid(String cid) {
        QueryWrapper<ClassTeacher> classTeacherQueryWrapper = new QueryWrapper<>();
        classTeacherQueryWrapper.eq(Constants.CLASS_ID,cid);
        ClassTeacher classTeacher = classTeacherDao.selectOne(classTeacherQueryWrapper);
        Teacher teacher = teacherDao.selectById(classTeacher.getTid());
        return teacher;
    }

    @Override
    public int deleteClassByCid(String cid) {
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        classQueryWrapper.eq(Constants.CLASS_ID,cid);
        int delete = classDao.delete(classQueryWrapper);

        return delete;
    }

    @Override
    public int addStudent(String student) {
        Student addStudent = JSON.parseObject(student, Student.class);
        int insert = studentDao.insert(addStudent);
        return insert;
    }

    @Override
    public int deleteStudent(String sid) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq(Constants.STUDENT_ID,sid);
        int delete = studentDao.delete(studentQueryWrapper);
        return delete;
    }

    @Override
    public Boolean login(String token) {
        Token token1 = JSON.parseObject(token, Token.class);
        if(Constants.ADMIN_ACCOUNT.equals(token1.getAccount())&&Constants.ADMIN_PASSWORD.equals(token1.getPassword())){
            return true;
        }
        return false;
    }


}
