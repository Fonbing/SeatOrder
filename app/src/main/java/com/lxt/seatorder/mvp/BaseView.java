package com.lxt.seatorder.mvp;

import android.content.Context;
import android.view.View;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public interface BaseView {
    /**
     * 显示正在加载view
     */
    void showLoadingDialog();

    /**
     * 关闭正在加载view
     */
    void dismissLoadingDialog();

    /**
     * 显示底部提示框（带按钮）
     *
     * @param dialogTitle      提示文字
     * @param leftBtn          左按钮
     * @param rightBtn         右按钮
     * @param leftBtnListener  左按钮事件
     * @param rightBtnListener 右按钮事件，默认为取消
     */
    void showBottomDialog(String dialogTitle, final String leftBtn, String rightBtn, View.OnClickListener leftBtnListener, View.OnClickListener rightBtnListener);

    /**
     * 关闭底部提示框
     */
    void dismissBottomDialog();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showErr();

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();
}
