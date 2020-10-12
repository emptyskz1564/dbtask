package com.liujiayi.clasip.util;

import lombok.Getter;
import lombok.ToString;

/**
 * 统一结果返回
 */
@Getter
@ToString
public class Result {

    //业务代码
    private String code;
    //信息描述
    private String message;
    //返回参数
    private Object data;

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(){
        return new Result(Constants.SUCCESS_CODE,Constants.SUCCESS_MSG,null);
    }

    public static Result successs(Object data){
        return new Result(Constants.SUCCESS_CODE,Constants.SUCCESS_MSG,data);
    }

    public static Result  failure(String code,String msg){
        return new Result(code,msg,null);
    }

    public static Result  failure(String code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result failure(ErrorEnum errorEnum){
        return new Result(errorEnum.getErrorCode(),errorEnum.getErrorMsg(),null);
    }

    public static Result failure(ErrorEnum errorEnum,Object data){
        return new Result(errorEnum.getErrorCode(),errorEnum.getErrorMsg(),data);
    }

}
