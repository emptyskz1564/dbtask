package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnetwork.adapters.GetStudentListAdapter;
import com.example.sqlnetwork.domain.ClassResult;
import com.example.sqlnetwork.domain.CommonResult;
import com.example.sqlnetwork.domain.Result;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

public class ClassStudentActivity extends AppCompatActivity {
    private GetStudentListAdapter adapter;
    private String tid;
    private String cid;
    TextView code;
    CommonResult commonResult;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_student);
        Intent intent = getIntent();
        tid = intent.getStringExtra("tid");
        cid = intent.getStringExtra("cid");

        initView();
        new init().run();
    }

    public void initView(){
        RecyclerView view = this.findViewById(R.id.recyclerView3);
        view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GetStudentListAdapter();
        view.setAdapter(adapter);


    }

    public void updateUI(final Result result){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setData(result);
            }
        });
    }

    public class init extends Thread {

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(UrlEnum.GET_STUDENT_BY_CID.getUrl() + cid);
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
                    Result result = gson.fromJson(body.string(), Result.class);
                    updateUI(result);
                }
            });

        }
    }

    public void getSignCode(View view){
        Request request = CommonUtil.getRequest(UrlEnum.GET_SIGN_CODE.getUrl() + cid + "/" + tid);
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
                commonResult = gson.fromJson(body.string(), CommonResult.class);
                if("200".equals(commonResult.getCode())){
                    code = findViewById(R.id.getCode);
                    updateUI();
                }
            }
        });
    }

    public void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                code.setText(commonResult.getMessage());
            }
        });
    }

}
