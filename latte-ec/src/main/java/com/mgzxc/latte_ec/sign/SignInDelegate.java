package com.mgzxc.latte_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.mgzxc.latte_core.delegates.LatteDelegate;
import com.mgzxc.latte_core.net.RestClient;
import com.mgzxc.latte_core.net.callback.ISuccess;
import com.mgzxc.latte_ec.R;
import com.mgzxc.latte_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by MG_ZXC on 2018/5/29.
 */
public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()) {
            RestClient.builder()
                    .url("")
                    .params("email",mEmail.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        getSupportDelegate().start(new SignUpDelegate());
    }
    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = false;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            mEmail.setError(null);
        }
        if (password.isEmpty()||password.length()<6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
