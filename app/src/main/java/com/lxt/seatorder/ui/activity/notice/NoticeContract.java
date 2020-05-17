package com.lxt.seatorder.ui.activity.notice;

import com.lxt.seatorder.bean.Notice;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NoticeContract {
    interface View extends BaseView {
        void getNoticeSucc(List<Notice> notices);

        void getNoticeFail(String msg);


    }

    interface Presenter extends BasePresenter<View> {
        void getNotice();
    }
}
