package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 刘斯昊
 */
@Mapper
@Repository
public interface ClassStudentDao extends BaseMapper<ClassStudent> {
}
