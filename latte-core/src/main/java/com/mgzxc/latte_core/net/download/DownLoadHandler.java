package com.mgzxc.latte_core.net.download;

import android.os.AsyncTask;

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
                        if (response.isSuccessful()) {
                           final ResponseBody body = response.body();
                           final SaveFileTask task=new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, body, NAME);
                            //这里注意判断 否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST!=null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR!=null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                        RestCreator.getParams().clear();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE!=null) {
                            FAILURE.onFailure();
                        }
                    }
                });

    }




}
