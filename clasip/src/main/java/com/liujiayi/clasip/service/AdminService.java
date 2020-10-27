package com.liujiayi.clasip.service;


import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Teacher;

import java.util.List;

/**
 * @author 刘斯昊
 */
public interface AdminService {

    /**
     * 获取所有课程列表
     * @return  课程列表
     */
    List<Class> getAllClass();

    /**
     * 获取课程对应的教师信息
     * @param cid 课程id
     * @return 教师实体类
     */
    Teacher getTeacherByCid(String cid);
}
