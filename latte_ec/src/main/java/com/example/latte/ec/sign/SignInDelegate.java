package com.example.latte.ec.sign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.app.delegates.LatteDelegate;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;


public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.et_email)
    TextInputEditText etEmail;
    @BindView(R2.id.et_password)
    TextInputEditText etPassword;
    @BindView(R2.id.bt_sign_in)
    Button btSignIn;
    @BindView(R2.id.tv_sign_in_wechat)
    TextView tvSignInWechat;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    private boolean checkForm() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            isPass = false;
            etPassword.setError("密码不能为空或者小于6位");
        } else {
            etPassword.setError(null);
        }

        return isPass;
    }

    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.bt_sign_in, R2.id.tv_sign_in_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.bt_sign_in:
                if (checkForm()) {
                    // TODO: 2019/9/4 请求登录接口
                }
                break;
            case R2.id.tv_sign_in_wechat:
                break;
        }
    }
}
