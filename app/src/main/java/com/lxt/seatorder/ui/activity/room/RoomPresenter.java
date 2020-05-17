package com.lxt.seatorder.ui.activity.room;

import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.RoomInfo;
import com.lxt.seatorder.bean.RoomOrderInfo;
import com.lxt.seatorder.mvp.BasePresenterImpl;
import com.lxt.seatorder.utils.JsonUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RoomPresenter extends BasePresenterImpl<RoomContract.View> implements RoomContract.Presenter {

    @Override
    public void getRoomInfo(final int roomId) {
        mAPIWrapper.getRoomInfo(roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.size() > 0) {
                            List<RoomInfo> roomInfoList = JsonUtils.json2List(jsonObject.getAsJsonArray("dirs").toString(), RoomInfo.class);
                            mView.getRoomInfoSuccess(roomInfoList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getRoomInfoFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRoomOrderInfo(int dirID, String userID, String jsonArray) {
        mAPIWrapper.getRoomOrderInfo(dirID, userID, jsonArray)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RoomOrderInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<RoomOrderInfo> roomOrderInfoList) {
                        mView.getRoomOrderInfoSuccess(roomOrderInfoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getRoomOrderInfoFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
