package com.lxt.seatorder.ui.activity.login;

import com.lxt.seatorder.api.Callback;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.bean.UserToken;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginContract {
    public interface View extends BaseView {
        //在View层回调，根据Presenter逻辑调用
        void loginSuccess(UserToken userToken);

        void loginFailed(String message);

        void loginError();

        void loginComplete();

        void getUserInfoSuccess(OrderUser orderUser);

        void getUserInfoSFail(String msg);

    }

    public interface Presenter extends BasePresenter<View> {
        //在View层调用，在Presenter中实现
        void login(String username, String password);

        void getUserInfo(Map<String, String> map);

    }

    public interface Model {
        //Model层调用，在Model中实现
        UserToken getAccessToken(boolean isRefreshToken, String username, String password,
                                 Callback<UserToken> callback);

    }
}
