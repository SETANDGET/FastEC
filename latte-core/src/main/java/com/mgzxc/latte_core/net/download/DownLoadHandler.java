package com.mgzxc.latte_core.net.download;

import com.mgzxc.latte_core.net.RestCreator;
import com.mgzxc.latte_core.net.callback.IError;
import com.mgzxc.latte_core.net.callback.IFailure;
import com.mgzxc.latte_core.net.callback.IRequest;
import com.mgzxc.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MG_ZXC on 2018/5/18.
 */
public final class DownLoadHandler {
    private final String URL;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    public DownLoadHandler(String URL, IRequest REQUEST, String DOWNLOAD_DIR,
                           String EXTENSION, String NAME, ISuccess SUCCESS,
                           IError ERROR, IFailure FAILURE) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
    }
    public void handleDownload(){
        //准备下载
        RestCreator.getRestService()
                .download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>(){
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

    }




}
