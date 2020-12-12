package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.pojo.Student;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class Register extends AppCompatActivity {

    EditText name;
    EditText sid;
    EditText password;
    EditText major;
    EditText grade;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = findViewById(R.id.name);
        sid = findViewById(R.id.login_sid);
        password = findViewById(R.id.password);
        major = findViewById(R.id.major);
        grade = findViewById(R.id.grade);

    }

    public void register(View view){
        Student student = new Student(sid.getText().toString(), password.getText().toString(), name.getText().toString(), major.getText().toString(), grade.getText().toString());
        final String requestBody = CommonUtil.getGson().toJson(student);

        Request postRequest = CommonUtil.postRequest(UrlEnum.REGISTER.getUrl(), requestBody);

        Call call = CommonUtil.getClient().newCall(postRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody body = response.body();
                System.out.println(response.code());
                System.out.println(body.toString());

                if(response.code() == 200){
                    startActivity(new Intent(Register.this,LoginActivity.class));
                } else {

                }

            }
        });
    }

    public void toLogin(View view){
        Intent intent = new Intent(Register.this, LoginActivity.class);
        startActivity(intent);
    }

}
