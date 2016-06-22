package edu.feicui.myapplication.db;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 操作与读取数据库的类
 * Created by 荆兴业 on 2016/4/28.
 */

public class AssetsDBManager {
    private static final String TAG = "AssetsDBManager";

    /**
     * 复制数据库的类
     */
    public static void copyAssetsFileToFile(Context context, String path, File toFilePath) throws IOException {
        Log.d(TAG, "copyAssetsFileToFile 开始:");
        Log.d(TAG, "文件路径:" + path);
        Log.d(TAG, "toFile 路径:" + toFilePath);

        InputStream is = null;
        //定义文件输入流
        BufferedInputStream bis = null;

        //定义文件输出流
        BufferedOutputStream bos = null;
        try {
            //拿到数据库的路径赋值给文件流
            is = context.getAssets().open(path);

            //定义输入流对象
            bis = new BufferedInputStream(is);

            // //TODO:2016/4/28 没有写false
            bos = new BufferedOutputStream(new FileOutputStream(toFilePath, false));

            int len = 0;

            byte[] buffer = new byte[2 * 1024];

            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
        } finally {
            bos.close();
            bis.close();
            is.close();

        }

    }
}
