package edu.feicui.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.myapplication.R;
import edu.feicui.myapplication.bean.TelClassInfo;


/**
 * Created by 荆兴业 on 2016/5/4.
 */
public class TelClassAdapter extends BaseAdapter {
    private ArrayList<TelClassInfo> mInfo = new ArrayList<>();

    private LayoutInflater mLayoutInflater;

    //构造方法 拿到inflate
    public TelClassAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public ArrayList<TelClassInfo> getDataFromAdpter() {
        return mInfo;
    }

    // 添加数据到当前适配器集合
    public void addDataToAdapter(TelClassInfo info) {
        if (info != null) {
            mInfo.add(info);
        }
    }

    // 添加数据到当前适配器集合(方法重载)
    public void addDataToAdapter(List<TelClassInfo> info) {
        if (info != null) {
            mInfo.addAll(info);
        }
    }

    //删除当前适配器集合内数据
    public void clearDataToAdapter() {
        mInfo.clear();
    }

    @Override
    public int getCount() {
        if (mInfo != null) {
            return mInfo.size();
        }
        return 0;
    }

    //返回值改成bean类型
    @Override
    public TelClassInfo getItem(int position) {
        return mInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.inflate_telmgr_listitem, null);
        }

        TextView tv_text = (TextView) convertView.findViewById(R.id.tv_title);
        tv_text.setText(getItem(position).name);
        return convertView;
    }
}
