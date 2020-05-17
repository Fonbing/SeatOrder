package com.lxt.seatorder.ui.activity.notice;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.Notice;
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

public class NoticePresenter extends BasePresenterImpl<NoticeContract.View> implements NoticeContract.Presenter {

    @Override
    public void getNotice() {
        mAPIWrapper.getNoticeInfo().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        JsonElement je = jsonObject.getAsJsonObject("data").get("notice");
                        if (!je.isJsonNull()) {
                            List<Notice> notices = JsonUtils.json2List(je.toString(), Notice.class);
                            mView.getNoticeSucc(notices);
                        } else mView.getNoticeFail("无公告");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getNoticeFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
