package com.example.financial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String pwd = "456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 登录事件
    public void login(View view) {
        // 获取用户名和密码
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();
        // 判断用户名和密码是否正确
        if (usernameStr.equals("123") && passwordStr.equals(pwd)) {
            // 跳转到主页面
            startActivity(new Intent(MainActivity.this, bar.class));
        } else {
            // 密码错误弹窗
            new AlertDialog.Builder(this)
                    .setTitle("登录失败")
                    .setMessage("账号或密码错误(账号：123，密码：" + pwd + ")")
                    .setPositiveButton("确定", null)
                    .show();
        }
    }
}