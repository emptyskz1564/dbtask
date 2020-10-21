package com.example.sqlnetwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.Result;
import com.example.sqlnetwork.util.CommonUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OkhttpActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        btn = findViewById(R.id.button2);
    }

    public void getRequest(View view){
        //创建客户端
        OkHttpClient okHttpClient = CommonUtil.getClient();
        //创建请求
        Request request = CommonUtil.getRequest("http://10.0.2.2:8080/allstudents");
        //创建请求任务
        Call call = okHttpClient.newCall(request);
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody body = response.body();
                Gson gson = CommonUtil.getGson();
                Result result = gson.fromJson(body.toString(), Result.class);
                List<Result.Student> students = result.getData();
                for (Result.Student student : students) {
                    System.out.println(students.toString());
                }
            }
        });
    }


}
