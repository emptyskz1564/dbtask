package com.example.sqlnetwork;

import android.content.Intent;
import android.location.LocationManager;
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
import com.example.sqlnetwork.domain.CommonResult;
import com.example.sqlnetwork.domain.SignResult;
import com.example.sqlnetwork.domain.StrResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

public class ClassInfoActivity extends AppCompatActivity {
    private String sid;
    private String cid;
    private SignResult.Sign sign;
    TextView msg;
    TextView className;
    TextView classCode;
    AClassResult classResult;
    SignResult signResult;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        Intent intent = getIntent();
        sid = intent.getStringExtra("sid");
        cid = intent.getStringExtra("cid");

        Init init = new Init(cid, UrlEnum.GET_CLASS_BY_CID.getUrl() + cid);
        System.out.println(sid);
        System.out.println(cid);

        init.run();
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
            System.out.println("开始初始化");
            Request request = CommonUtil.getRequest(url);
            Call call = CommonUtil.getClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    ResponseBody body = response.body();
                    if(response.code() == 200){
                        String string = body.string();
                        System.out.println(string);
                        classResult = CommonUtil.getGson().fromJson(string, AClassResult.class);
                        System.out.println(classResult);
                        if(classResult.getCode().equals("200")){
                            className = findViewById(R.id.ClassName_ClassInfo);
                            classCode = findViewById(R.id.ClassCode_ClassInfo);
                            msg = findViewById(R.id.msg_ClassInfo);
                            updateUI();
                            Request request1 = CommonUtil.getRequest("http://192.168.124.6:8080/student/getSign/" + cid);
                            Call call1 = CommonUtil.getClient().newCall(request1);
                            call1.enqueue(new Callback() {
                                @Override
                                public void onFailure(Request request, IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(Response response) throws IOException {
                                    if(response.code()==200){
                                        System.out.println("请求成功");
                                        ResponseBody body = response.body();
                                        String string1 = body.string();
                                        System.out.println(string1);
                                        Gson gson = CommonUtil.getGson();
                                        signResult = gson.fromJson(string1, SignResult.class);
                                        System.out.println(signResult);
                                        if("200".equals(signResult.getCode()) && signResult.getData() != null && signResult.getData().size() != 0){
                                            sign = signResult.getData().get(0);

                                            updateUI1();
                                        }
                                    }
                                }
                            });
                        }

                    } else {
                        System.out.println("请求失败");
                    }


                }
            });


        }
    }

    public void sign(View view){
        if(sign==null){
            System.out.println("暂无签到");
        } else {
            EditText signCode = findViewById(R.id.SignCode_ClassInfo);
            //https://hailicy.xyz/clasip/student/signUp/{sid}/{cid}/{version}/{lng}/{lat}
            Request request = CommonUtil.getRequest("https://hailicy.xyz/clasip/student/signUp/" + sid + "/" + cid + "/" + signCode.getText() + "/");
            OkHttpClient client = CommonUtil.getClient();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if(response.code() == 200){
                        ResponseBody body = response.body();
                        Gson gson = CommonUtil.getGson();
                        CommonResult commonResult = gson.fromJson(body.string(), CommonResult.class);
                        if("200".equals(commonResult.getCode())){
                            System.out.println("签到成功");
                        } else {
                            System.out.println("签到失败");
                        }
                    }
                }
            });

        }
    }

    public void updateUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                msg.setText("暂无签到");
                className.setText(classResult.getData().getClassName());
                classCode.setText(classResult.getData().getCid());
            }
        });
    }
    public void updateUI1(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                msg.setText("您有一条正在进行的签到，发起时间:" + signResult.getData().get(0).getStart_time());
            }
        });
    }
}
