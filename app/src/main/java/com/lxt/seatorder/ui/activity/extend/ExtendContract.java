package com.lxt.seatorder.ui.activity.extend;

import com.google.gson.JsonObject;
import com.lxt.seatorder.bean.ScheduleTime;
import com.lxt.seatorder.mvp.BasePresenter;
import com.lxt.seatorder.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ExtendContract {
    interface View extends BaseView {
        void getScheduleTaskSucc(JsonObject taskSchedules);

        void getScheduleTaskFail(String msg);

        void addScheduleTaskSucc(String msg,int type);

        void addScheduleTaskFail(String msg,int type);

        void updateScheduleStatusSucc(String msg,int type);

        void updateScheduleStatusFail(String msg,int type);
    }

    interface Presenter extends BasePresenter<View> {
        void getScheduleTask(String userId, int type);

        void addScheduleTask(ScheduleTime scheduleTime);

        void updateScheduleStatus(String userId, int status, int type);
    }
}
