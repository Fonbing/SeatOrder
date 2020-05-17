package com.lxt.seatorder.mvp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lxt.seatorder.MyApplication;
import com.lxt.seatorder.utils.StatusBarUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends BaseActivity {


    private MaterialDialog mLoadingDialog;
    private Toast mToast;
    protected MyApplication mApp;
    protected Context mContext;
    private Dialog mBottomAlertDialog;
    private Dialog mEditDialog;
    private Dialog mRefreshDialog;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceID());
        mPresenter = getInstance(this, 1);
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
//        StatusBarUtil.StatusBarLightMode(this);     //设置白底黑字
        mApp = (MyApplication) getApplication();
        mContext = this;
        initView();
        initListener();
        initData();
    }


    protected abstract int getLayoutResourceID();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    public <T> T getInstance(Object o, int i) {
        try {
            Type type = o.getClass().getGenericSuperclass();
            if (!(type instanceof ParameterizedType)) {
                return (T) Object.class;
            } else
                return ((Class<T>) ((ParameterizedType) type).getActualTypeArguments()[i])
                        .newInstance();
        } catch (Fragment.InstantiationException e) {
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
