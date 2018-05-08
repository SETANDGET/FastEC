package com.mgzxc.latte_core.net;

import com.mgzxc.latte_core.net.callback.IError;
import com.mgzxc.latte_core.net.callback.IFailure;
import com.mgzxc.latte_core.net.callback.IRequest;
import com.mgzxc.latte_core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by MG_ZXC on 2018/5/8.
 * 采用建造者模式
 */
public class RestClient {
    private final String URL;
    private final IRequest REQUEST;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String url, IRequest request,
                      Map<String, Object> params,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body) {
        this.URL = url;
        this.REQUEST = request;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }



}
