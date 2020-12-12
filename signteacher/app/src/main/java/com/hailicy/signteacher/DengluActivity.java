package com.hailicy.signteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.okhttp.*;

import androidx.appcompat.app.AppCompatActivity;



public class DengluActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);

        Button bt = findViewById(R.id.button3);
        final EditText editText = findViewById(R.id.editTextTextPersonName);
        final EditText ed2 = findViewById(R.id.editTextTextPassword);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tid = editText.getText().toString();
                String pwd = ed2.getText().toString();


                //网络请求部分
                try {
                    URL url = new URL("https://hailicy.xyz/clasip/teacherlogin/"+tid+"/"+pwd);




                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                //跳转到新页面




            }
        });



    }
}