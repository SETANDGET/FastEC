package com.mgzxc.latte_core.net;

import android.content.Context;

import com.mgzxc.latte_core.net.callback.IError;
import com.mgzxc.latte_core.net.callback.IFailure;
import com.mgzxc.latte_core.net.callback.IRequest;
import com.mgzxc.latte_core.net.callback.ISuccess;
import com.mgzxc.latte_core.net.callback.RequestCallbacks;
import com.mgzxc.latte_core.ui.LatteLoader;
import com.mgzxc.latte_core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

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
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url, IRequest request,
                      Map<String, Object> params,
                      ISuccess success,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        this.REQUEST = request;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST!=null) {
            REQUEST.onRequestStart();//开始请求
        }
        if (LOADER_STYLE!=null) {
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call!=null) {
            call.enqueue(getRequestCallBacks());//调用自定义的callback
        }
    }
    private Callback<String> getRequestCallBacks() {
        return new RequestCallbacks(REQUEST, SUCCESS, ERROR, FAILURE,LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
