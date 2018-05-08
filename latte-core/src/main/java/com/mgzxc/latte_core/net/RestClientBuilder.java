package com.mgzxc.latte_core.net;

import com.mgzxc.latte_core.net.callback.IError;
import com.mgzxc.latte_core.net.callback.IFailure;
import com.mgzxc.latte_core.net.callback.IRequest;
import com.mgzxc.latte_core.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by MG_ZXC on 2018/5/8.
 * 建造者模式
 */
public class RestClientBuilder {
    private String mUrl;
    private IRequest mIRequest;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private ISuccess mISuccess;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }
    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }
    public final RestClientBuilder error(IError iError){
        this.mIError = iError;
        return this;
    }
    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }
    public final RestClient build(){
        return new RestClient(mUrl,mIRequest,PARAMS,mISuccess,mIError,mIFailure,mBody);
    }



}
