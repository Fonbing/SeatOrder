package com.lxt.seatorder.ui.activity.room;

import com.lxt.seatorder.bean.RoomInfo;
import com.lxt.seatorder.bean.RoomOrderInfo;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RoomContract {
    interface View extends BaseView {

        void getRoomOrderInfoSuccess(List<RoomOrderInfo> roomOrderInfoList);

        void getRoomOrderInfoFail(String msg);

        void getRoomInfoSuccess(List<RoomInfo> roomInfoList);

        void getRoomInfoFail(String msg);

    }

    interface Presenter extends BasePresenter<View> {
        void getRoomOrderInfo(int dirID, String userID, String jsonArray);

        void getRoomInfo(int roomId);
    }
}
