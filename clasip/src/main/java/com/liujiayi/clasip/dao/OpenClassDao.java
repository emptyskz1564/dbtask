package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.association.OpenClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 创建时间: 2020-11-22 16:52
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Repository
@Mapper
public interface OpenClassDao extends BaseMapper<OpenClass> {
}
