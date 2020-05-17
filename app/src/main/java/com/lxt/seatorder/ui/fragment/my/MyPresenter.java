package com.lxt.seatorder.ui.fragment.my;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.User;
import com.lxt.seatorder.mvp.BasePresenterImpl;
import com.lxt.seatorder.utils.JsonUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyPresenter extends BasePresenterImpl<MyContract.View> implements MyContract.Presenter {

    @Override
    public void getMyUserInfo(String userId) {
        mAPIWrapper.getMyUserinfo(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        JsonElement je = jsonObject.getAsJsonObject("data").get("user");
                        if (!je.isJsonNull()) {
                            User user = JsonUtils.json2Entity(je.toString(), User.class);
                            mView.getMyUserInfoSucc(user);
                        } else mView.getMyUserInfoFail("获取用户信息失败");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getMyUserInfoFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
