package com.liujiayi.clasip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建时间: 2020-12-08 21:40
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class testController {

    @ResponseBody
    @GetMapping("/test")
    public Object test1(){
        return "这是一个测试接口~";
    }

}
