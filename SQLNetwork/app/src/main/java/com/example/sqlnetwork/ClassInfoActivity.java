package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.AClassResult;
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

public class ClassInfoActivity extends AppCompatActivity {
    private String sid;
    private String cid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        Intent intent = getIntent();
        sid = intent.getStringExtra("sid");
        cid = intent.getStringExtra("cid");

        Init init = new Init(cid, UrlEnum.GET_CLASS_BY_CID.getUrl() + cid);
        init.start();
    }

    public void toSign(String cid){
        Intent intent = new Intent(ClassInfoActivity.this, SignActivity.class);
        intent.putExtra("cid",cid);

        startActivity(intent);
    }


    public class Init extends Thread{
        private String cid;
        private String url;

        public Init(String sid, String url) {
            this.cid = cid;
            this.url = url;
        }

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(url+cid);
            Call call = CommonUtil.getClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    ResponseBody body = response.body();
                    AClassResult classResult = CommonUtil.getGson().fromJson(body.toString(), AClassResult.class);

                    if(classResult.getCode().equals("200")){
                        TextView className = findViewById(R.id.ClassName_ClassInfo);
                        TextView classCode = findViewById(R.id.ClassCode_ClassInfo);
                        className.setText(classResult.getaClass().getClassName());
                        classCode.setText(classResult.getaClass().getCid());
                    }
                }
            });

            Request request1 = CommonUtil.getRequest("https://hailicy.xyz/clasip/student/getSign/" + cid);
            Call call1 = CommonUtil.getClient().newCall(request1);
            call1.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {

                }
            });
        }
    }
}
