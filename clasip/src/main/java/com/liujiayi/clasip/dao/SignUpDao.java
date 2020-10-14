package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.SignUp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 创建时间: 2020-10-14 21:01
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Mapper
@Repository
public interface SignUpDao extends BaseMapper<SignUp> {
}
