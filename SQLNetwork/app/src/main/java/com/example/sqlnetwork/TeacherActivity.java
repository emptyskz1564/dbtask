package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnetwork.adapters.GetClassListAdapter;
import com.example.sqlnetwork.domain.ClassResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class TeacherActivity extends AppCompatActivity {
    private String tid;
    private GetClassListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Intent intent = getIntent();
        tid = intent.getStringExtra("tid");

        initView();
        new init(tid).run();

    }


    public void initView(){
        RecyclerView view = this.findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GetClassListAdapter();
        adapter.setOnItemClickListener(new GetClassListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });
        view.setAdapter(adapter);


    }

    public void updateUI(final ClassResult classResult){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setData(classResult);
            }
        });
    }

    public class init extends Thread {
        private String tid;

        init(String tid){
            this.tid = tid;
        }

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(UrlEnum.ALL_CLASS_BY_TID.getUrl() + tid);
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
                    ClassResult classResult = gson.fromJson(body.string(), ClassResult.class);
                    updateUI(classResult);
                }
            });

        }
    }
}
