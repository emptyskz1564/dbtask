package com.example.sqlnetwork.util;

public enum UrlEnum {

    REGISTER("注册链接",""),
    ALL_CLASS("获取全部课程链接",""),
    STUDENT_INFO("获取个人信息",""),
    ADD_TO_CLASS("加入课程链接",""),
    LOGIN("登录链接","");

    private String msg;
    private String url;

    UrlEnum(String msg,String url){
        this.msg = msg;
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public String getUrl() {
        return url;
    }
}
