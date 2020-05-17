package com.lxt.seatorder.ui.fragment.history;

import com.google.gson.JsonArray;
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

public class HistoryPresenter extends BasePresenterImpl<HistoryContract.View> implements HistoryContract.Presenter {

    @Override
    public void getOrderHistory(String userId, int page, int size, int type) {
        mAPIWrapper.getOrderHistory(userId, page, size, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject orderRecordObj) {
                        String records = orderRecordObj.getAsJsonArray("records").toString();
                        List<OrderRecord> orderRecords = JsonUtils.json2List(records, OrderRecord.class);
                        mView.getOrderHistorySuccess(orderRecords);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getOrderHistoryFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
