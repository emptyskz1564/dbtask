package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.Token;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    EditText sid;
    EditText pwd;
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sid = findViewById(R.id.login_sid);
        pwd = findViewById(R.id.login_pwd);
        login = findViewById(R.id.login);
    }

    public void login(View view){
        Token token = new Token(sid.getText().toString(), pwd.getText().toString());

        String json = CommonUtil.getGson().toJson(token);

        Request request = CommonUtil.postRequest(UrlEnum.LOGIN.getUrl(), json);

        Call call = CommonUtil.getClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.code() == 200){
                    System.out.println("登录成功");
                    Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                    intent.putExtra("sid",sid.getText().toString());

                    startActivity(intent);

                } else {
                    System.out.println("密码或学号错误");
                }
            }
        });

    }

    public void goRegister(View view){
        startActivity(new Intent(LoginActivity.this,Register.class));
    }

}
