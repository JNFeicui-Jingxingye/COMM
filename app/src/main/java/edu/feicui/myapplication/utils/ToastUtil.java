package edu.feicui.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/23 0023.
 * <p>
 * Toast管理工具类,有且只有一个Toast对象进行展示操作
 */
public class ToastUtil {
    private static Toast toast;

    public static void show(Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        }
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }
}
