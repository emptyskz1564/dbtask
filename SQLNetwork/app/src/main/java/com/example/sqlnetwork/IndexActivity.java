package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnetwork.adapters.GetClassListAdapter;
import com.example.sqlnetwork.domain.ClassResult;
import com.example.sqlnetwork.domain.CommonResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class IndexActivity extends AppCompatActivity {

    private GetClassListAdapter adapter;
    private String sid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Intent intent = getIntent();
        sid = intent.getStringExtra("sid");
        System.out.println(sid);
        initView();
        new init(sid).run();
    }

    public void initView(){
        RecyclerView view = this.findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GetClassListAdapter();
        adapter.setOnItemClickListener(new GetClassListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(IndexActivity.this, ClassInfoActivity.class);
                intent.putExtra("cid",adapter.getData().get(position).getCid());
                intent.putExtra("sid",sid);
                startActivity(intent);
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
        private String sid;

        init(String sid){
            this.sid = sid;
        }

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(UrlEnum.ALL_CLASSES_BY_SID.getUrl() + sid);
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
                    ClassResult classResult = gson.fromJson(body.toString(), ClassResult.class);
                    updateUI(classResult);
                }
            });

        }
    }

    public void addClass(View view){
        EditText editText = findViewById(R.id.Class_Code);
        String classCode = editText.getText().toString();
        Request request = CommonUtil.getRequest(UrlEnum.ADD_TO_CLASS + sid + "/" + classCode);
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
                CommonResult commonResult = gson.fromJson(body.string(), CommonResult.class);
                if("200".equals(commonResult.getCode())){
                    new init(sid).run();
                }
            }
        });
    }


}
