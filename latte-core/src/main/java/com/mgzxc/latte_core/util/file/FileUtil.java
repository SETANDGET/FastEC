package com.mgzxc.latte_core.util.file;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import com.mgzxc.latte_core.app.Latte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by MG_ZXC on 2018/5/10.
 */
public final class FileUtil {
    //时间格式化模板
    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    //sd卡路径
    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();
    //默认本地上传图片的目录
    public static final String UPLOAD_PHOTO_DIR = Environment.getExternalStorageDirectory().getPath() + "/a_upload_photos/";
    //网页缓存地址
    public static final String WEB_CACHE_DIR = Environment.getExternalStorageDirectory().getPath() + "/app_web_cache/";

    //系统相机目录
    public static final String CAMERA_PHOTO_DIR = Environment.getDownloadCacheDirectory().getPath() + "/Carmera/";


    //格式化时间
    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * @param timeFormatHeader 用时间格式化文件名
     * @param extension        文件后缀名
     * @return 返回时间格式化的文件名
     */
    public static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader + "." + extension);
    }

    //构成完整路径
    private static File createDir(String sdcardDirName) {
        final String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static File createFile(String sdcardDirName, String fileName) {
        return new File(createDir(sdcardDirName), fileName);
    }

    /**
     * @param sdcardDirName    sd卡路径
     * @param timeformatHeader 时间格式
     * @param extension        后缀名
     * @return
     */
    public static File createFileByTime(String sdcardDirName, String timeformatHeader, String extension) {
        final String fileName = getFileNameByTime(timeformatHeader, extension);
        return createFile(sdcardDirName, fileName);
    }

    //获取文件的mime
    public static String getMimeType(String filePath) {
        final String extension = getExtension(filePath);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    //获取文件后缀名
    private static String getExtension(String filePath) {
        String suffix = "";
        final File file = new File(filePath);
        final String name = file.getName();
        final int idx = name.lastIndexOf('.');
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    public static File saveBitmap(Bitmap bitmap, String dir, int compress) {
        final String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File fileName = createFileByTime(dir, "DOWN_LOAD", "jpg");

        try {
            fos = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fos);
            bitmap.compress(Bitmap.CompressFormat.JPEG, compress, bos);//把数据写到文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {


            try {
                if (bos != null) {
                    bos.flush();
                }
                if (bos!=null) {
                    bos.close();
                }
                if (fos!=null) {
                    fos.flush();
                }
                if (fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        refreshDCIM();//存贮照片后需要刷新DCIM
        return fileName;
    }

    //通知系统刷新系统相册，使照片展现出来
    private static void refreshDCIM() {
        if (Build.VERSION.PREVIEW_SDK_INT>=19) {
            //兼容Android 4.4版本，只扫面存放照片的目录
            MediaScannerConnection.scanFile(Latte.getApplicationContext(), new String[]{Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()}, null, null);
        }else{
            //扫描整个SD卡来更新系统图库，当文件很多时候用户体验不佳，且不适合4.4以上版本
            Latte.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }

    //读取 raw目录里的文件，并返回字符串
    public static String getRawFile(int id){
        final InputStream is = Latte.getApplicationContext().getResources().openRawResource(id);
        final BufferedInputStream bis = new BufferedInputStream(is);
        final InputStreamReader isr = new InputStreamReader(bis);
        final BufferedReader br = new BufferedReader(isr);
        final StringBuilder sb = new StringBuilder();
        String str;
        try {
            if ((str=br.readLine())!=null) {
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                isr.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return sb.toString();
    }

    //设置字体 样式  引入第三方字体
    public static void  setIconFont(String path, TextView textView){
        final Typeface typeface=Typeface.createFromAsset(Latte.getApplicationContext().getAssets(), path);
        textView.setTypeface(typeface);
    }
    public static String getAssetsFile(String name){
        final  AssetManager assetManager = Latte.getApplicationContext().getAssets();
        InputStream is=null;
        BufferedInputStream bis=null;
        InputStreamReader isr = null;
        StringBuilder sb=null;
        BufferedReader br=null;
        try {
             is = assetManager.open(name);
            bis = new BufferedInputStream(is);
            isr = new InputStreamReader(bis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            String  str;
            if ((str=br.readLine())!=null) {
                sb.append(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                isr.close();;
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static String  getRealFilePath(Context context,Uri uri){
        if (uri==null) return null;
        final String scheme = uri.getScheme();
        String path=null;
        if(scheme==null){
            path = uri.getPath();
        }else if (ContentResolver.SCHEME_FILE.equals(scheme)){
            path = uri.getPath();
        }else if (ContentResolver.SCHEME_CONTENT.equals(scheme)){
            final Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (cursor!=null) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index>-1) {
                        path = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }

        return path;
    }


}
