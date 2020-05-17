package com.lxt.seatorder.ui.activity.signinorout;

import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SignInOrOutContract {
    public interface View extends BaseView {
        void signInOrOutSuccess(String msg);

        void signInOrOutFail(String msg);

        void getInuseSeatSuccess(OrderRecord orderRecord);

        void getInuseSeatFail(String msg);

        void getSeatNameSuccess(List<String> seats);

        void getSeatNameFail(String msg);


    }

    public interface Presenter extends BasePresenter<View> {
        void SignInOrOut(int OrderItemId, int status, String userId);

        void getInuseSeat(String userId);

        void getSeatName(int seatId);

    }
}
