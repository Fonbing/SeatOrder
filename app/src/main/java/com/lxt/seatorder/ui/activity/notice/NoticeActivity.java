package com.lxt.seatorder.ui.activity.notice;


import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.Notice;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.ui.adapter.RvNoticeHistoryAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class NoticeActivity extends MVPBaseActivity<NoticeContract.View, NoticePresenter> implements NoticeContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    private BGARefreshLayout mRlNoticeRefresh;
    private RecyclerView mRvNoticeHistory;
    private RvNoticeHistoryAdapter mRvNoticeHistoryAdapter;
    private List<Notice> mNoticeRecordList;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_notice;
    }

    @Override
    protected void initView() {
        mRlNoticeRefresh = findViewById(R.id.rl_notice_refresh);
        mRvNoticeHistory = findViewById(R.id.rv_notice_history);

        initRefreshLayout();
        initRvAnim();
        setTopTitle("公告");
        addBackButton();
        mRvNoticeHistory.setAdapter(mRvNoticeHistoryAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getNotice();
    }

    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRlNoticeRefresh.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        // 设置下拉刷新和上拉加载更多的风格
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mRlNoticeRefresh.setRefreshViewHolder(meiTuanRefreshViewHolder);
    }

    private void initRvAnim() {
        mRvNoticeHistoryAdapter = new RvNoticeHistoryAdapter(mRvNoticeHistory);
        mRvNoticeHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_slide_in_bottom);
        mRvNoticeHistory.setLayoutAnimation(animationController);
        mRvNoticeHistory.getItemAnimator().setChangeDuration(1);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getNotice();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void getNoticeSucc(List<Notice> notices) {
        dismissLoadingDialog();
        mNoticeRecordList = notices;
        mRvNoticeHistoryAdapter.setData(notices);
        mRlNoticeRefresh.endRefreshing();
    }

    @Override
    public void getNoticeFail(String msg) {
        dismissLoadingDialog();
        mRlNoticeRefresh.endRefreshing();
        showToast(msg);
        showNoDataText();
    }
}
