package com.mgzxc.latte_core.net.callback;

import com.mgzxc.latte_core.ui.LatteLoader;
import com.mgzxc.latte_core.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MG_ZXC on 2018/5/8.
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADERSTYLE;

    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure, LoaderStyle loaderStyle) {
        this.LOADERSTYLE = loaderStyle;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        // 无论什么情况都应该有请求结束的回调
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADERSTYLE != null) {
            LatteLoader.stopLoading();
        }
    }


}
