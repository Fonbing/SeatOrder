package com.lxt.seatorder.ui.activity.personinfo;

import com.lxt.seatorder.bean.User;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PersonInfoContract {
    interface View extends BaseView {

        void updateUserInfoSucc(User user,String msg, int type);

        void updateUserInfoFail(String msg,int type);

    }

    interface Presenter extends BasePresenter<View> {
        void updateUserInfo(User user,int type);
    }
}
