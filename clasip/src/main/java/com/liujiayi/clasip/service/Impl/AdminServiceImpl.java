package com.liujiayi.clasip.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.ClassTeacherDao;
import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.pojo.association.TeacherClass;
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

    @Override
    public List<Class> getAllClass() {
        return classDao.selectList(null);
    }

    @Override
    public Teacher getTeacherByCid(String cid) {
        QueryWrapper<TeacherClass> classTeacherQueryWrapper = new QueryWrapper<>();
        classTeacherQueryWrapper.eq(Constants.CLASS_ID,cid);
        TeacherClass classTeacher = classTeacherDao.selectOne(classTeacherQueryWrapper);
        Teacher teacher = teacherDao.selectById(classTeacher.getTid());
        return teacher;
    }


}
