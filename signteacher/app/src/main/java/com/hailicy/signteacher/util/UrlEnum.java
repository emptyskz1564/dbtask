package com.hailicy.signteacher.util;

public enum UrlEnum {

    REGISTER("注册链接","https://hailicy.xyz/clasip/student/register"),
    ALL_CLASSES_BY_SID("获取全部课程链接","http://192.168.43.71:8080/student/getAllClassBySid/"),
    STUDENT_INFO("获取个人信息",""),
    ADD_TO_CLASS("加入课程链接","http://192.168.43.71:8080/student/addStudentToClass/"),
    LOGIN("登录链接","https://hailicy.xyz/clasip/student/login"),
    GET_CLASS_BY_CID("获取单个课程信息","http://192.168.43.71:8080/student/getClassInfoByCid/");

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
