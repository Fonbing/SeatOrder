package com.lxt.seatorder.ui.fragment.my;

import com.lxt.seatorder.bean.User;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyContract {
    interface View extends BaseView {
        void getMyUserInfoSucc(User user);

        void getMyUserInfoFail(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyUserInfo(String userId);
    }
}
