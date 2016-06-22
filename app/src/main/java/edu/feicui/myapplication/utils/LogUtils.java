package edu.feicui.myapplication.utils;

import android.util.Log;

/**
 * 打印Log日志类
 * Created by 荆兴业 on 2016/4/28.
 */
public class LogUtils {
    public static boolean isOpenDebug = true;
    public static boolean isOpenWarn = true; /*Debug日志*/

    public static void d(String tag, String msg) {
        if (isOpenDebug)
            Log.d(tag, msg);
    } /*Warning日志*/

    public static void w(String tag, String msg) {
        if (isOpenWarn)
            Log.w(tag, msg);
    }
}
