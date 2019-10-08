package com.example.latte.ec.sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.app.delegates.LatteDelegate;
import com.google.android.material.textfield.TextInputEditText;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册逻辑
 */
public class SignUpDelegate extends LatteDelegate {


    @BindView(R2.id.et_user_name)
    TextInputEditText etUserName;
    @BindView(R2.id.et_email)
    TextInputEditText etEmail;
    @BindView(R2.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R2.id.et_password)
    TextInputEditText etPassword;
    @BindView(R2.id.et_second_password)
    TextInputEditText etSecondPassword;
    @BindView(R2.id.bt_sign_up)
    Button btSignUp;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.bt_sign_up)
    public void onViewClicked() {
        if (checkForm()){
//            RestClient.builder()
//                    .url("sign_up")
//                    .params("","")
//                    .onSuccess(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    }).build().post();
            Toast.makeText(_mActivity,"验证通过",Toast.LENGTH_LONG).show();
        }
    }


    private boolean checkForm(){
        String name = etUserName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();
        String rePassword = etSecondPassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            etUserName.setError("请输入姓名");
            isPass = false;
        }else {
            etUserName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            etEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            etPhone.setError("手机号码错误");
            isPass = false;
        }else {
            etPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            isPass = false;
            etPassword.setError("密码不能为空或者小于6位");
        }else {
            etPassword.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || !password.equals(rePassword)) {
            isPass = false;
            etSecondPassword.setError("密码验证错误");
        }else {
            etSecondPassword.setError(null);
        }

        return isPass;
    }
}
