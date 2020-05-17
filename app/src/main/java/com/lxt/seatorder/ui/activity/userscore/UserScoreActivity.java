package com.lxt.seatorder.ui.activity.userscore;


import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.bean.UserScore;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.ui.adapter.RvScoreHistoryAdapter;
import com.lxt.seatorder.ui.widget.CircleTextView;
import com.lxt.seatorder.utils.SpUtils;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class UserScoreActivity extends MVPBaseActivity<UserScoreContract.View, UserScorePresenter> implements UserScoreContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    private CircleTextView mCtvScoreCurrent;
    private BGARefreshLayout mRlScoreRefresh;
    private RecyclerView mRvScoreHistory;
    private RvScoreHistoryAdapter mRvScoreHistoryAdapter;
    private List<UserScore> mScoreRecordList;


    private int page = 1;
    private int pagesize = 10;
    private int mMorePageNumber = 0;
    private int mNewPageNumber = 0;

    private OrderUser mUserInfo;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_score_history;
    }

    @Override
    protected void initView() {
        setTopTitle("我的积分");
        addBackButton();
        mCtvScoreCurrent = findViewById(R.id.ctv_score_current);
        mRlScoreRefresh = findViewById(R.id.rl_score_refresh);
        mRvScoreHistory = findViewById(R.id.rv_score_history);

        initRefreshLayout();
        initRvAnim();
        mRvScoreHistory.setAdapter(mRvScoreHistoryAdapter);
        if (!SpUtils.isContains(mContext, OrderUser.class.getSimpleName())) {
//            mPresenter.getUserInfo();
        } else {
            showLoadingDialog();
            mUserInfo = SpUtils.getBean(mContext, OrderUser.class);
            mPresenter.getScoreHistory(mUserInfo.getUserId(), 1, 10);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void getScoreSuccess(List<UserScore> userScores) {
        dismissLoadingDialog();
        if (page == 1) {
            mScoreRecordList = userScores;
            mCtvScoreCurrent.setText(String.valueOf(userScores.get(0).getSurplusScore()));
            mRvScoreHistoryAdapter.setData(userScores);
        } else {
            mRvScoreHistoryAdapter.addMoreData(userScores);
        }
        mRlScoreRefresh.endRefreshing();
        mRlScoreRefresh.endLoadingMore();

    }

    @Override
    public void getScoreFail(String msg) {
        dismissLoadingDialog();
        mRlScoreRefresh.endRefreshing();
        mRlScoreRefresh.endLoadingMore();
        showToast(msg);
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRlScoreRefresh.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        // 设置下拉刷新和上拉加载更多的风格
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mRlScoreRefresh.setRefreshViewHolder(meiTuanRefreshViewHolder);
    }

    private void initRvAnim() {
        mRvScoreHistoryAdapter = new RvScoreHistoryAdapter(mRvScoreHistory);
        mRvScoreHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_slide_in_bottom);
        mRvScoreHistory.setLayoutAnimation(animationController);
        mRvScoreHistory.getItemAnimator().setChangeDuration(0);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mNewPageNumber++;
//        showToast("没有最新数据了");
        mMorePageNumber = 0;
        mPresenter.getScoreHistory(mUserInfo.getUserId(), page = 1, pagesize);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber > 5) {
            mRlScoreRefresh.endLoadingMore();
            showToast("没有更多数据了");
            return false;
        }
        mPresenter.getScoreHistory(mUserInfo.getUserId(), ++page, pagesize);
        return true;
    }
}
