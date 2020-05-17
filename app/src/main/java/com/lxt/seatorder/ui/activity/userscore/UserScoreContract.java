package com.lxt.seatorder.ui.activity.userscore;

import com.lxt.seatorder.bean.UserScore;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UserScoreContract {
    interface View extends BaseView {
        void getScoreSuccess(List<UserScore> userScores);

        void getScoreFail(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getScoreHistory(String userId, int page, int size);
    }
}
