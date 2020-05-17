package com.lxt.seatorder.ui.fragment.history;

import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HistoryContract {
    interface View extends BaseView {
        void getOrderHistorySuccess(List<OrderRecord> orderRecordList);

        void getOrderHistoryFail(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getOrderHistory(String userId, int page, int size, int type);
    }
}
