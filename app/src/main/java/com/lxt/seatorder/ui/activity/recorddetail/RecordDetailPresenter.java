package com.lxt.seatorder.ui.activity.recorddetail;

import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.mvp.BasePresenterImpl;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecordDetailPresenter extends BasePresenterImpl<RecordDetailContract.View> implements RecordDetailContract.Presenter {

    @Override
    public void getRecordDetail(int orderItemId) {
        mAPIWrapper.getRecordDetail(orderItemId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderRecord>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderRecord orderRecord) {
                        mView.getRecordDetailSuccess(orderRecord);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getRecordDetailFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void cancelOrder(int OrderItemId, int status, String userId) {
        mAPIWrapper.updateOrderStatus(OrderItemId, status, userId, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject != null) {
                            mView.canceleOrderSucc("取消预约成功");
                        } else mView.cancelOrderFail("取消预约失败");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.cancelOrderFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
