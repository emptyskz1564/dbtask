package com.liujiayi.clasip.service;

import com.liujiayi.clasip.pojo.Student;

import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
public interface StudentService {

    /**
     * 获取某个班级的所有学生
     * @param cid   班级id
     * @return 学生列表
     */
    List<Student> getAllStudentByCid(String cid);

    /**
     * 根据学号获取指定学生
     * @param sid   学生学号
     * @return 某特定学生
     */
    Student getStudentBySid(String sid);




}
