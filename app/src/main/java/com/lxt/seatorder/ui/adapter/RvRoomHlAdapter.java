package com.lxt.seatorder.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.RoomInfo;
import com.lxt.seatorder.ui.activity.room.RoomHlActivity;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

import static com.lxt.seatorder.utils.AppConfig.TAG;

/**
 * Created by Lxt Jxfen on 2019-12-06.
 * email: 1771874056@qq.com
 */
public class RvRoomHlAdapter extends BGARecyclerViewAdapter<RoomInfo> {

    private Activity mActivity;
    private Context mContext;
    RecyclerView mRecyclerView;
    private List<RoomInfo> mRoomInfoList;
    private int[] mLayoutID;
    private TextView mTvRoomFloor;


    public RvRoomHlAdapter(RecyclerView recyclerView, List<RoomInfo> roomInfoList, Activity activity, int[] layoutID) {
        super(recyclerView);
        mRecyclerView = recyclerView;
        mRoomInfoList = roomInfoList;
        mActivity = activity;
        mLayoutID = layoutID;
        mTvRoomFloor = mActivity.findViewById(R.id.tv_room_floor);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, RoomInfo roomInfo) {
        mContext = helper.getConvertView().getContext();
        ViewGroup desk = ((ViewGroup) helper.getConvertView());
        boolean isFillRow = desk.getChildCount() > 1;   //是否为最后填充的行
//        int t = position;
        TableLayout tl;
        Log.i(TAG, "fillData:childcount " + desk.getChildCount());
        for (int i = 0; i < desk.getChildCount(); i++) {
            CardView cardView;
            if (isFillRow) {
                cardView = (CardView) (ViewGroup) (desk.getChildAt(i));
                tl = (TableLayout) cardView.getChildAt(0);
            } else {
                cardView = (CardView) desk;
                tl = (TableLayout) (desk.getChildAt(0));
            }
            if (mTvRoomFloor.getText().toString().equals("回廊5层") && mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                if (position >= 20 && position < (mRoomInfoList.size() - 18)) {
                    if (position % 4 == 0 || position % 4 == 1)
                        cardView.setVisibility(View.GONE);
                }
            }
            char c = 'A';
            int rowCount = tl.getChildCount();
            int x = 0;
            for (int row = 0; row < rowCount; row++) {   //填充座位
                final ViewGroup tr = (ViewGroup) tl.getChildAt(row);
                int columnCount = tr.getChildCount();
                String dirName = mRoomInfoList.get(position + i).getDirName();
                if (row == 1) {  //第二行
                    TextView mTvDeskName = ((TextView) (tr.getChildAt(0)));
                    mTvDeskName.setText(dirName);
                } else for (int column = 0; column < columnCount; column++) {
                    final TextView mTvSeatName = ((TextView) (((ViewGroup) (tr.getChildAt(column))).getChildAt(0)));
                    RoomInfo.Seat seat = mRoomInfoList.get(position + i).getResources().get(x++);  //二维数组在一维里是第几个元素
                    mTvSeatName.setText(seat.getResourceSysName());  //座位号
                    mTvSeatName.setEnabled(!(seat.getSeatStatus() == 21));
                    mTvSeatName.setTag(seat);
                    mTvSeatName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((RoomHlActivity) (mActivity)).onClick(view);  //回调交给activity处理
                        }
                    });
                }
            }
        }
    }


    @Override
    public int getItemCount() {
//        return mRoomInfoList.size() - (mRoomInfoList.size() % 5) + 1;     //16
        return mRoomInfoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mLayoutID[0];
    }

}
