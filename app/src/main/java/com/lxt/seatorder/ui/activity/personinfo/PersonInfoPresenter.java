package com.lxt.seatorder.ui.activity.personinfo;

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

public class PersonInfoPresenter extends BasePresenterImpl<PersonInfoContract.View> implements PersonInfoContract.Presenter {

    @Override
    public void updateUserInfo(final User user, final int type) {
        mAPIWrapper.addMyUserInfo(user).subscribeOn(Schedulers.io())
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
                            mView.updateUserInfoSucc(user, jsonObject.get("msg").toString(), type);
                        } else mView.updateUserInfoFail("获取用户信息失败", type);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.updateUserInfoFail(ErrorHandler.errorMessage(e), type);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
