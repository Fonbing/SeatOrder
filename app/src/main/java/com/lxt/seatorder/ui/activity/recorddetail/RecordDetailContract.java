package com.lxt.seatorder.ui.activity.recorddetail;

import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecordDetailContract {
    interface View extends BaseView {
        void getRecordDetailSuccess(OrderRecord orderRecord);

        void getRecordDetailFail(String msg);

        void canceleOrderSucc(String msg);

        void cancelOrderFail(String msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getRecordDetail(int orderItemId);

        void cancelOrder(int OrderItemId, int status, String userId);

    }
}
