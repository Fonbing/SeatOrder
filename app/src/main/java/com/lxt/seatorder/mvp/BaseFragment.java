package com.lxt.seatorder.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lxt.seatorder.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Lxt Jxfen on 2019-12-11.
 * email: 1771874056@qq.com
 */
public class BaseFragment extends Fragment implements BaseView {

    BaseActivity mActivity;
    private View mRootView;
    private Toast mToast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (BaseActivity) getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity.overridePendingTransition(R.anim.view_fade_in, R.anim.view_fade_out);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void showLoadingDialog() {
        checkActivityAttached();
        mActivity.showLoadingDialog();
    }

    @Override
    public void dismissLoadingDialog() {
        checkActivityAttached();
        mActivity.dismissLoadingDialog();

    }

    protected void showBottomAlertDialog(String title, String line1, String line2, String finishBtn, final String leftBtn, String rightBtn, View.OnClickListener leftBtnListener) {
        mActivity.showBottomAlertDialog(title, line1, line2, finishBtn, leftBtn, rightBtn, leftBtnListener);

    }

    protected void dismissBottomAlertDialog() {
        mActivity.dismissBottomAlertDialog();

    }

    protected void showNoDataText() {
        mActivity.showNoDataText();

    }

    @Override
    public void showBottomDialog(String dialogTitle, String leftBtn, String rightBtn, View.OnClickListener leftBtnListener, View.OnClickListener rightBtnListener) {
        mActivity.showBottomDialog(dialogTitle, leftBtn, rightBtn, leftBtnListener, rightBtnListener);
    }

    @Override
    public void dismissBottomDialog() {
        mActivity.dismissBottomDialog();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public void startActivity(Class<?> activity) {
        checkActivityAttached();
        mActivity.startActivity(activity);

    }

    @Override
    public void showErr() {
        checkActivityAttached();
        mActivity.showErr();
    }

    protected boolean isAttachedContext() {
        return getActivity() != null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity.overridePendingTransition(R.anim.view_fade_in, R.anim.view_fade_out);
    }


    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
