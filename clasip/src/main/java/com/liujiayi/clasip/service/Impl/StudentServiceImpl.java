package com.liujiayi.clasip.service.Impl;

import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired StudentDao studentDao;

    @Override
    public List<Student> getAllStudentByCid(String cid) {

        return null;
    }

    @Override
    public Student getStudentBySid(String sid) {
        return studentDao.selectById(sid);
    }
}

