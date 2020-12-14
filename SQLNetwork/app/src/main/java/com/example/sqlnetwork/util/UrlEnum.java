package com.example.sqlnetwork.util;

public enum UrlEnum {

    REGISTER("注册链接","https://hailicy.xyz/clasip/student/register"),
    ALL_CLASSES_BY_SID("获取全部课程链接","https://hailicy.xyz/clasip/student/getAllClassBySid/"),
    STUDENT_INFO("获取个人信息",""),
    ADD_TO_CLASS("加入课程链接","https://hailicy.xyz/clasip/admin/addStudentToClass4/"),
    LOGIN("登录链接","https://hailicy.xyz/clasip/student/login"),
    TEACHER_LOGIN("教师登录","http://192.168.124.6:8080/teacher/teacherlogin/"),
    ALL_CLASS_BY_TID("老师获取所有课程","https://hailicy.xyz/clasip/admin/getAllClassByTid/"),
    GET_STUDENT_BY_CID("获取某班级所有学生","https://hailicy.xyz/clasip/cidToAllStus/"),
    GET_SIGN_CODE("发起签到获取签到码","https://hailicy.xyz/clasip/teacher/sign/"),
    GET_SIGN("获取签到","https://hailicy.xyz/clasip/teacher/getsign2/"),
    GET_CLASS_BY_CID("获取单个课程信息","https://hailicy.xyz/clasip/student/getClassInfoByCid/");

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
