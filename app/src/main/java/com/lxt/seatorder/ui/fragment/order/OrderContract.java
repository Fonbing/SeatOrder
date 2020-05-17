package com.lxt.seatorder.ui.fragment.order;

import com.google.gson.JsonArray;
import com.lxt.seatorder.bean.OrderInfo;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderContract {
    interface View extends BaseView {


        void orderSuccess(OrderInfo orderInfo);

        void orderFail(String msg);
    }

    interface Presenter extends BasePresenter<OrderContract.View> {
        void submitOrder(String auth, int dirid, int resourceid, JsonArray payload);

    }
}
