package com.example.sqlnetwork.domain;

import java.util.List;

public class AClassResult {
    private String code;
    private String message;
    private Class aClass;


    public class Class {
        String cid;
        String className;

        public Class(String cid, String className) {
            this.cid = cid;
            this.className = className;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        @Override
        public String toString() {
            return "Class{" +
                    "cid='" + cid + '\'' +
                    ", className='" + className + '\'' +
                    '}';
        }
    }

    public AClassResult(String code, String message, Class aClass) {
        this.code = code;
        this.message = message;
        this.aClass = aClass;
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

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public String toString() {
        return "AClassResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", aClass=" + aClass +
                '}';
    }
}
