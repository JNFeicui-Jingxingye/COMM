package edu.feicui.myapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//      initAppDBFile();
        mAdapter.clearDataToAdapter();
        mAdapter.addDataToAdapter(new TelClassInfo("本地电话",0));
        try {
            mAdapter.addDataToAdapter(DBReader.readTeldbClasslist());
        }catch (Exception e){
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();
    }

//    数据库是否存在
//    private void initAppDBFile() {
//        if (DBReader.isExistsTeldbFile()){
//            try {
//                AssetsDBManager.copyAssetsFileToFile(getApplicationContext(),"db/commonnum.db",DBReader.telFile);
//            } catch (IOException e) {
//                Toast.makeText(TelMsgActivity.this,"数据库异常",Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            }
//        }
//    }

    private void initView() {
        mLvTelList = (ListView) findViewById(R.id.lv_tel_list);
        mAdapter = new TelClassAdapter(this);
        mLvTelList.setAdapter(mAdapter);
        mLvTelList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==0){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            startActivity(intent);
        }
    }
}
