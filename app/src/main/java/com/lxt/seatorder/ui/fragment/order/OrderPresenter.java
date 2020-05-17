package com.lxt.seatorder.ui.fragment.order;

import android.util.Log;

import com.google.gson.JsonArray;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.OrderInfo;
import com.lxt.seatorder.mvp.BasePresenterImpl;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.JsonUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 * 邮箱 bing61@qq.com
 */

public class OrderPresenter extends BasePresenterImpl<OrderContract.View> implements OrderContract.Presenter {

    @Override
    public void submitOrder(String auth, int dirid, int resourceid, JsonArray payload) {
        mAPIWrapper.submitOrder(auth, dirid, resourceid, payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonArray jsonArray) {
                        Log.i(AppConfig.TAG, "onNext: " + jsonArray.toString());
                        OrderInfo orderInfo = JsonUtils.json2Entity(jsonArray.get(0).getAsJsonObject().toString(), OrderInfo.class);
                        mView.orderSuccess(orderInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.orderFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
