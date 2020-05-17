package com.lxt.seatorder.ui.fragment.history;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.mvp.MVPBaseFragment;
import com.lxt.seatorder.ui.adapter.RvOrderHistoryAdapter;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public class HistoryFragment extends MVPBaseFragment<HistoryContract.View, HistoryPresenter> implements HistoryContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {
    private BGARefreshLayout mRlHistoryRefresh;
    private RecyclerView mRvOrderHistory;
    private RvOrderHistoryAdapter mRvOrderHistoryAdapter;
    private List<OrderRecord> mOrderRecordList = new ArrayList<>();

    private int page = 1;
    private int pagesize = 10;
    private int mMorePageNumber = 0;
    private int mNewPageNumber = 0;
    private int type = 1;

    private OrderUser mUserInfo;

    @Override
    protected void initView() {
        mRlHistoryRefresh = (BGARefreshLayout) findViewById(R.id.rl_address_refresh);
        mRvOrderHistory = (RecyclerView) findViewById(R.id.rv_order_history);
        initRvAnim();
        initRefreshLayout();
        mRvOrderHistoryAdapter = new RvOrderHistoryAdapter(mRvOrderHistory);
        mRvOrderHistory.setAdapter(mRvOrderHistoryAdapter);
        if (!SpUtils.isContains(mActivity, OrderUser.class.getSimpleName())) {
//            mPresenter.getUserInfo();
        } else {
            showLoadingDialog();
            mUserInfo = SpUtils.getBean(mActivity, OrderUser.class);
            mPresenter.getOrderHistory(mUserInfo.getUserId(), 1, 10, 1);
        }
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_history;
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mNewPageNumber++;
//        showToast("没有最新数据了");
        mMorePageNumber = 0;
        mPresenter.getOrderHistory(mUserInfo.getUserId(), page = 1, pagesize, type);
    }


    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber > 5) {
            mRlHistoryRefresh.endLoadingMore();
            showToast("没有更多数据了");
            return false;
        }
        mPresenter.getOrderHistory(mUserInfo.getUserId(), ++page, pagesize, type);
        return true;
    }


    @Override
    public void getOrderHistorySuccess(List<OrderRecord> orderRecordList) {
        dismissLoadingDialog();
        if (page == 1) {
            mOrderRecordList = orderRecordList;
            mRvOrderHistoryAdapter.setData(orderRecordList);
            SpUtils.putBean(getContext(), "orderRecordList", orderRecordList);
        } else {
            mRvOrderHistoryAdapter.addMoreData(orderRecordList);
        }
        mRlHistoryRefresh.endRefreshing();
        mRlHistoryRefresh.endLoadingMore();
    }

    @Override
    public void getOrderHistoryFail(String msg) {
        dismissLoadingDialog();
        mRlHistoryRefresh.endRefreshing();
        mRlHistoryRefresh.endLoadingMore();
        showNoDataText();
        showToast(msg);
    }


    private void initRefreshLayout() {
        // 为BGARefreshLayout 设置代理
        mRlHistoryRefresh.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(getContext(), true);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        // 设置下拉刷新和上拉加载更多的风格
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        mRlHistoryRefresh.setRefreshViewHolder(meiTuanRefreshViewHolder);
    }

    private void initRvAnim() {
        mRvOrderHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_anim_slide_in_bottom);
        mRvOrderHistory.setLayoutAnimation(animationController);
    }


}
