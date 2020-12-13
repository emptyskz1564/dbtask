package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建时间: 2020-12-13 14:18
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class ClassController {

    @Autowired
    ClassDao classDao;

    //搜索接口
    @ResponseBody
    @GetMapping("/search/{info}")
    public Object mysearch(@PathVariable("info")String info){
        List<Class> classList = classDao.mysearch("%"+info+"%");
        if(classList.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else {
            return Result.successs2(classList);
        }
//        return classList;
    }

}