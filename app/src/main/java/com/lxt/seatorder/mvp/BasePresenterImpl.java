package com.lxt.seatorder.mvp;

import com.lxt.seatorder.api.APIWrapper;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {
    protected V mView;
    protected APIWrapper mAPIWrapper;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    @Override
    public void attachView(V view) {
        mView = view;
        if (mAPIWrapper == null)
            mAPIWrapper = APIWrapper.getInstance(mView.getContext());

    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    @Override
    public void detachView() {
        mView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mView;
    }

}
