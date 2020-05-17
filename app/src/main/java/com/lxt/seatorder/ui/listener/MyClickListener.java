package com.lxt.seatorder.ui.listener;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Lxt Jxfen on 2019-12-08.
 * email: 1771874056@qq.com
 */
public class MyClickListener implements View.OnTouchListener {

    private static int timeout = 400;//双击间四百毫秒延时
    private int clickCount = 0;//记录连续点击次数
    private Handler handler;
    private MyClickCallBack myClickCallBack;

    public interface MyClickCallBack {
        void oneClick();//点击一次的回调

        void doubleClick();//连续点击两次的回调

    }


    public MyClickListener(MyClickCallBack myClickCallBack) {
        this.myClickCallBack = myClickCallBack;
        handler = new Handler();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickCount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (clickCount == 1) {
                        myClickCallBack.oneClick();
                    } else if (clickCount == 2) {
                        myClickCallBack.doubleClick();
                    }
                    handler.removeCallbacksAndMessages(null);
                    //清空handler延时，并防内存泄漏
                    clickCount = 0;//计数清零
                }
            }, timeout);//延时timeout后执行run方法中的代码
        }
        return false;//让点击事件继续传播，方便再给View添加其他事件监听
    }


    public static class ClickProxy implements View.OnClickListener {

        private View.OnClickListener origin;
        private long lastclick = 0;
        private long timems; //ms

        public ClickProxy(View.OnClickListener origin, long timems) {
            this.origin = origin;
            this.timems = timems;
        }

        @Override
        public void onClick(View v) {
            if (System.currentTimeMillis() - lastclick >= timems) {
                origin.onClick(v);
                lastclick = System.currentTimeMillis();
            } else {
                Toast.makeText(v.getContext(), "点击过快", Toast.LENGTH_SHORT).show();
            }
        }
    }


}