package com.example.sqlnetwork.domain;

import java.io.Serializable;
import java.util.List;

public class ClassResult {
    private String code;
    private String message;
    private List<Class> data;


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

    public ClassResult(String code, String message, List<Class> data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public List<Class> getData() {
        return data;
    }

    public void setData(List<Class> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
