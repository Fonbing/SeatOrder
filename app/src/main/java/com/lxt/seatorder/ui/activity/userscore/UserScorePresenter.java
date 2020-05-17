package com.lxt.seatorder.ui.activity.userscore;

import com.google.gson.JsonObject;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.UserScore;
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

public class UserScorePresenter extends BasePresenterImpl<UserScoreContract.View> implements UserScoreContract.Presenter {


    @Override
    public void getScoreHistory(String userId, int page, int size) {
        mAPIWrapper.getScore(userId,page,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        String records = jsonObject.getAsJsonArray("records").toString();
                        List<UserScore> scoreList = JsonUtils.json2List(records, UserScore.class);
                        mView.getScoreSuccess(scoreList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getScoreFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
