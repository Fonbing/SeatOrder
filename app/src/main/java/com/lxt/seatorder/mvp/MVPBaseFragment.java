package com.lxt.seatorder.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */

public abstract class MVPBaseFragment<V extends BaseView, T extends BasePresenterImpl<V>> extends BaseFragment {

//    protected O mPresenter;


    private View mRootView;
    protected T mPresenter;
    protected Activity mActivity;

    protected abstract void initView();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    public abstract int getLayoutResourceID();

    public abstract void initListener();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(getLayoutResourceID(), container, false);
        mActivity = getActivity();
        mPresenter = getInstance(this, 1);
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        initView();
        initAllMembersView(savedInstanceState);
        initListener();
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected <K extends View> K findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        return (K) mRootView.findViewById(id);
    }

    public <T> T getInstance(Object o, int i) {
        try {
            Type type = o.getClass().getGenericSuperclass();
            if (!(type instanceof ParameterizedType)) {
                return (T) Object.class;
            } else
                return ((Class<T>) ((ParameterizedType) type).getActualTypeArguments()[i])
                        .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
