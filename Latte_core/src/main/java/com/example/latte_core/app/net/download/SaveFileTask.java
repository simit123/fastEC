package com.example.latte_core.app.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.PrecomputedText;

import com.example.latte_core.app.Latte;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;
import com.example.latte_core.app.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object,Void, File> {


    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downLoadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downLoadDir == null || downLoadDir.equals("")) {
            downLoadDir = "down_loads";
        }

        if (extension == null || extension.equals("")) {
            extension = "";
        }

        if (name == null) {
            return FileUtil.writeToDisk(is,downLoadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(is,name,extension);
        }
    }


    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstanceAPK(file);
    }


    /**
     * 如果是apk结尾的文件自动安装
     * @param file
     */
    private void autoInstanceAPK(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
