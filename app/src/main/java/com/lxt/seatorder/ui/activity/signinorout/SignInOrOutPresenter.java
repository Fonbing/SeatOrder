package com.lxt.seatorder.ui.activity.signinorout;

import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.OrderRecord;
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

public class SignInOrOutPresenter extends BasePresenterImpl<SignInOrOutContract.View> implements SignInOrOutContract.Presenter {

    @Override
    public void SignInOrOut(int OrderItemId, int status, String userId) {
        mAPIWrapper.updateOrderStatus(OrderItemId, status, userId,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject != null) {
                            mView.signInOrOutSuccess("成功");
                        } else mView.signInOrOutFail("失败");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.signInOrOutFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getInuseSeat(String userId) {
        mAPIWrapper.getInuseSeat(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.get("code") != null && jsonObject.get("code").getAsInt() == 3112)
                            mView.getInuseSeatFail(jsonObject.get("msg").getAsString());
                        else {
                            OrderRecord info = JsonUtils.json2Entity(jsonObject.toString(), OrderRecord.class);
                            mView.getInuseSeatSuccess(info);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getInuseSeatFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getSeatName(int seatId) {
        mAPIWrapper.getSeatName(seatId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        mView.getSeatNameSuccess(strings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getInuseSeatFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
