package edu.feicui.myapplication.bean;

import edu.feicui.myapplication.main.TelMsgActivity;

/**
 * Created by 荆兴业 on 2016/5/3.
 */
//获取数据库 classlist 里的数据
public class TelClassInfo {
    public String name;
    public int idx;

    //重写构造方法
    public TelClassInfo(String name, int idx) {
        super();
        this.name = name;
        this.idx = idx;
    }

    public TelClassInfo() {
        super();
    }

}
