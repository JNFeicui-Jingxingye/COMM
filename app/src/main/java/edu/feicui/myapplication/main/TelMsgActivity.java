package edu.feicui.myapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import edu.feicui.myapplication.R;
import edu.feicui.myapplication.adapter.TelClassAdapter;
import edu.feicui.myapplication.bean.TelClassInfo;
import edu.feicui.myapplication.db.AssetsDBManager;
import edu.feicui.myapplication.db.DBReader;
import edu.feicui.myapplication.entity.TelclassInfo;
import edu.feicui.myapplication.utils.ToastUtil;

/**
 * 通讯录页面
 */
public class TelMsgActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mLvTelList;
    private TelClassAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_msg);

        mLvTelList = (ListView) findViewById(R.id.lv_tel_list);
        mAdapter = new TelClassAdapter(this);
        mLvTelList.setAdapter(mAdapter);
        mLvTelList.setOnItemClickListener(this);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //适配数据
        initAppDBFile();
        mAdapter.clearDataToAdapter();
        mAdapter.addDataToAdapter(new TelClassInfo("本地电话", 0));
        try {
            mAdapter.addDataToAdapter(DBReader.readTeldbClasslist());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// db库内的电话分类
        mAdapter.notifyDataSetChanged();
    }

    //    数据库是否存在
    private void initAppDBFile() {
        if (DBReader.isExistsTeldbFile()) {
            try {
                // 将本地项目中的Assets/db/commonnum.db文件复制写出到 DBRead.telFile文件中
                AssetsDBManager.copyAssetsFileToFile(getApplicationContext(), "db/commonnum.db", DBReader.telFile);
            } catch (IOException e) {
//                Toast.makeText(TelMsgActivity.this, "数据库异常", Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
                ToastUtil.show(this, "数据库异常", Toast.LENGTH_SHORT);
            }
        }
    }

    private void initView() {
        mLvTelList = (ListView) findViewById(R.id.lv_tel_list);
        mAdapter = new TelClassAdapter(this);
        mLvTelList.setAdapter(mAdapter);
        mLvTelList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 本地通话
        if (position == 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
            return;
        }
        // 取出当前选择的选项实体内容
        TelClassInfo classInfo = mAdapter.getItem(position);
        // 跳转至电话浏览页面,且传入idx
        Intent intent = new Intent(this, TellistActivity.class);
        intent.putExtra("idx", classInfo.idx);
        startActivity(intent);
    }

    private long preTime = 0;
    private long curTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            curTime = System.currentTimeMillis();
            if (curTime - preTime <= 800) {
                finish();
            }
            ToastUtil.show(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT);
            preTime = curTime;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
