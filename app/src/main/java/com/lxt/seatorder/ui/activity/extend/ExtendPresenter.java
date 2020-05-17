package com.lxt.seatorder.ui.activity.extend;

import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.ScheduleTime;
import com.lxt.seatorder.mvp.BasePresenterImpl;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ExtendPresenter extends BasePresenterImpl<ExtendContract.View> implements ExtendContract.Presenter {

    @Override
    public void getScheduleTask(String userId, int type) {
        mAPIWrapper.getScheduleTask(userId, type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject != null) {
                            JsonObject data = jsonObject.getAsJsonObject("data");
                            mView.getScheduleTaskSucc(data);
                        } else mView.getScheduleTaskFail("获取服务失败");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getScheduleTaskFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addScheduleTask(final ScheduleTime scheduleTime) {
        mAPIWrapper.addScheduleTask(scheduleTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("code").getAsInt() == 10200) {
                            String msg = jsonObject.get("msg").getAsString();
                            mView.addScheduleTaskSucc(msg, scheduleTime.getType());
                        } else {
                            String msg = jsonObject.get("msg").getAsString();
                            mView.addScheduleTaskFail(msg, scheduleTime.getType());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.addScheduleTaskFail(ErrorHandler.errorMessage(e), scheduleTime.getType());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateScheduleStatus(String userId, int status, final int type) {
        mAPIWrapper.updateScheduleStatus(userId, status, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("code").getAsInt() == 10200) {
                            String msg = jsonObject.get("msg").getAsString();
                            mView.updateScheduleStatusSucc(msg, type);
                        } else {
                            String msg = jsonObject.get("msg").getAsString();
                            mView.updateScheduleStatusFail(msg, type);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.updateScheduleStatusFail(ErrorHandler.errorMessage(e), type);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
