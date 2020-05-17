package com.lxt.seatorder.ui.adapter;

import com.lxt.seatorder.bean.RecordDetail;

import java.util.List;

/**
 * Created by Lxt Jxfen on 2020/4/6.
 * email: 1771874056@qq.com
 */
public interface NodeProgressAdapter {
    /**
     * 返回集合大小
     *
     * @return
     */
    int getCount();

    /**
     * 适配数据的集合
     *
     * @return
     */
    List<RecordDetail> getData();


}
