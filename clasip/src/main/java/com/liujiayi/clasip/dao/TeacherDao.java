package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 创建时间: 2020-10-14 21:00
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Mapper
@Repository
public interface TeacherDao extends BaseMapper<Teacher> {

    @Select("select * from Teacher wher tid=#{tid} and pwd=#{pwd}")
    public Teacher tealogin(@Param("tid")String tid,@Param("pwd")String pwd);

}
