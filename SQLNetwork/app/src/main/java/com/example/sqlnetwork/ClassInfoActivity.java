package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.ClassResult;
import com.example.sqlnetwork.domain.StrResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.Scanner;

public class ClassInfoActivity extends AppCompatActivity {
    private String sid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        Intent intent = getIntent();
        sid = intent.getStringExtra("sid");

        Init init = new Init(sid, UrlEnum.ALL_CLASSES_BY_SID.getUrl() + sid);
        init.start();
    }

    public void addClass(String cid){
        Request request = CommonUtil.getRequest(UrlEnum.ADD_TO_CLASS.getUrl() + sid + cid);
        Call call = CommonUtil.getClient().newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody body = response.body();
                Gson gson = CommonUtil.getGson();
                StrResult strResult = gson.fromJson(body.toString(), StrResult.class);

                if(strResult.getCode().equals("200")){

                }
            }
        });
    }

    public void toSign(String cid){
        Intent intent = new Intent(ClassInfoActivity.this, SignActivity.class);
        intent.putExtra("cid",cid);

        startActivity(intent);
    }


    public class Init extends Thread{
        private String sid;
        private String url;

        public Init(String sid, String url) {
            this.sid = sid;
            this.url = url;
        }

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(url+sid);
            Call call = CommonUtil.getClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    ResponseBody body = response.body();
                    ClassResult classResult = CommonUtil.getGson().fromJson(body.toString(), ClassResult.class);

                    if(classResult.getCode().equals("200")){

                    }
                }
            });
        }
    }
}
