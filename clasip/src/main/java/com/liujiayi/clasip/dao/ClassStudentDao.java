package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 刘斯昊
 */
@Mapper
@Repository
public interface ClassStudentDao extends BaseMapper<ClassStudent> {

    //传入学生号，查询我的所有课程
    @Select("select b.* from clasip.StudentClass as a,clasip.Class as b where a.sid=#{sid} and a.cid=b.cid")
    public List<Class> getMyClass(@Param("sid")String sid);

}
