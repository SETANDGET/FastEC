package com.mgzxc.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mgzxc.latte_core.delegates.LatteDelegate;
import com.mgzxc.latte_core.net.RestClient;
import com.mgzxc.latte_core.net.callback.IError;
import com.mgzxc.latte_core.net.callback.IFailure;
import com.mgzxc.latte_core.net.callback.IRequest;
import com.mgzxc.latte_core.net.callback.ISuccess;

/**
 * Created by MG_ZXC on 2018/5/8.
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();

    }

    private void testRestClient() {
        Toast.makeText(getContext(), "测试", Toast.LENGTH_SHORT).show();
        RestClient.builder()
                .url("http://10.0.3.2:8080/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("HAHAH", response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                       // final ArrayList<Interceptor> INTERCEPTORS=Latte.getConfiguration(ConfigType.INTERCEPTOR);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();


                    }
                })
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build().get();
    }
}
