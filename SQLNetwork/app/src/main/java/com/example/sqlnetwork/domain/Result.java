package com.example.sqlnetwork.domain;

import java.util.Arrays;
import java.util.List;

public class Result {
    private String code;
    private String message;
    private List<Student> data;

    public class Student {
        private Integer sid;
        private String pwd;
        private String name;
        private String major;
        private String className;
        private String grade;

        public Student(Integer sid, String pwd, String name, String major, String className, String grade) {
            this.sid = sid;
            this.pwd = pwd;
            this.name = name;
            this.major = major;
            this.className = className;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "sid=" + sid +
                    ", pwd='" + pwd + '\'' +
                    ", name='" + name + '\'' +
                    ", major='" + major + '\'' +
                    ", className='" + className + '\'' +
                    ", grade='" + grade + '\'' +
                    '}';
        }

        public Integer getSid() {
            return sid;
        }

        public void setSid(Integer sid) {
            this.sid = sid;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public Result(String code, String message, List<Student> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
