package com.mgzxc.latte_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.mgzxc.latte_core.app.Latte;
import com.mgzxc.latte_core.net.callback.IRequest;
import com.mgzxc.latte_core.net.callback.ISuccess;
import com.mgzxc.latte_core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by MG_ZXC on 2018/5/22.
 */
public class SaveFileTask extends AsyncTask <Object,Void,File>{
    public SaveFileTask(IRequest REQUEST, ISuccess ISUCCESS) {
        this.REQUEST = REQUEST;
        this.ISUCCESS = ISUCCESS;
    }

    private final IRequest REQUEST;
    private final ISuccess ISUCCESS;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected File doInBackground(Object... objects) {
         String downloadDir= ((String) objects[0]);
         String extension= ((String) objects[1]);
        ResponseBody body= ((ResponseBody) objects[2]);
        String name= ((String) objects[3]);
        InputStream is = body.byteStream();
        if (downloadDir==null||downloadDir.equals("")){
            downloadDir = "down_loads";
        }
        if (extension==null||extension.equals("")){
            extension = "";
        }
        if (name==null){
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        }else{
            return FileUtil.writeToDisk(is, downloadDir, name);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }


    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (ISUCCESS!=null) {
            ISUCCESS.onSuccess(file.getPath());
            if (REQUEST!=null) {
                REQUEST.onRequestEnd();
            }
        }

        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);

        }
    }


}
