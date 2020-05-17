package com.lxt.seatorder.mvp;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public interface BasePresenter<V extends BaseView> {
    public void attachView(V view);

    public void detachView();

}
