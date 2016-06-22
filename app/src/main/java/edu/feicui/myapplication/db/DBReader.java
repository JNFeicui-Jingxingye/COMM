package edu.feicui.myapplication.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.myapplication.bean.TelClassInfo;
import edu.feicui.myapplication.bean.TelNumberInfo;

/**
 * 数据库文件的读取
 * Created by 荆兴业 on 2016/5/3.
 */
public class DBReader {
    private static final String TAG = "DBReader";
    public static final String DB_PATH = "data/data/edu.feicui.myapplication/";
    public static File telFile;

    {
        File fileDir = new File(DB_PATH);
        fileDir.mkdir();//文件目录创建
        telFile = new File(DB_PATH, "commonnum.db");
        Log.d(TAG, "telFile path:" + DB_PATH);
    }

    //创建返回值为 boolean 的 isExistsTeldbFile()方法判断文件 是否存在
    public static boolean isExistsTeldbFile() {
        File toFile = DBReader.telFile;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 创建readTeldbClasslist(),readTeldbTable()方法读取数 据库文件
     * readTeldbClasslist()：读取 telFile 这个数据库文件中的 classlist 这个表内的数据
     */
    public static ArrayList<TelClassInfo> readTeldbClasslist() {
        ArrayList<TelClassInfo> infos = new ArrayList<TelClassInfo>();
        //打开DB文件
        SQLiteDatabase db = null;
        // 执行查询的 SQL 语句 select * from classlist
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("select * from classlist", null);
            Log.d(TAG, "read teldb classlist size: " + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    //idx 为 classlist 表中电话的 ID，根据 idx 值进行指定页面的跳转
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                    TelClassInfo classInfo = new TelClassInfo(name, idx);
                    infos.add(classInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        } finally {
            try {
                cursor.close();
                db.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            Log.d(TAG, "读取数据:" + infos.size());
        }
        return infos;
    }

    /**
     * readTeldbTable：读取 telFile 这个数据库文件中的 table 这个表内的数据
     *
     * @return
     */
    public static ArrayList<TelNumberInfo> readTeldbTable(int idx) {
        ArrayList<TelNumberInfo> numberInfo = new ArrayList<>();
        //idx 为 classlist 表中电话的 ID，根据 idx 值进行指定页面的跳转
        String sql = "select * from table" + idx;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            //打开DB文件
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            // 执行查询的 SQL 语句 select * from table +idx
            cursor = db.rawQuery(sql, null);
            Log.d(TAG, "read teldb number table size:" + cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));

                    TelNumberInfo telNumberInfo = new TelNumberInfo(name, number);
                    numberInfo.add(telNumberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
                db.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            Log.d(TAG, "读取数据:" + numberInfo.size());
        }
        return numberInfo;
    }
}
