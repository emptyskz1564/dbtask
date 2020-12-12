package com.example.sqlnetwork.util;
import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.concurrent.TimeUnit;

public class CommonUtil {

    private CommonUtil(){}

    private static Gson gson = new Gson();
    private static OkHttpClient client = new OkHttpClient();

    public static Gson getGson(){
        return gson;
    }

    public static OkHttpClient getClient(){
        client.setConnectTimeout(5000,TimeUnit.MILLISECONDS);
        return client;
    }

    /**
     * @author 刘斯昊
     * @param url 请求链接
     * @return 返回一个不包含请求体的request请求
     */
    public static Request getRequest(String url){
        Request request = new Request.Builder().get().url(url).build();
        return request;
    }

    /**
     *
     * @param url   请求链接
     * @param requestBody   请求体
     * @return  返回一个包含请求体的request请求
     */
    public static Request postRequest(String url,String requestBody){
        MediaType parse = MediaType.parse("application/json");
        RequestBody Body = RequestBody.create(parse,requestBody);
        Request request = new Request.Builder().post(Body).url(url).build();
        return request;
    }

}
