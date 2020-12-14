package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        return Result.successs2("这是一段测试文字~");
    }


    @ResponseBody
    @GetMapping("/test2/{str}")
    public Object test1(@PathVariable("str")String str){


        return Result.successs2("这是你请求发过来的的内容： "+str);
    }

}
