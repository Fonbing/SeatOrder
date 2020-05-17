package com.lxt.seatorder.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.RoomInfo;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

import static com.lxt.seatorder.utils.AppConfig.TAG;

/**
 * Created by Lxt Jxfen on 2019-12-06.
 * email: 1771874056@qq.com
 */
public class RvRoomAdapter extends BGARecyclerViewAdapter<RoomInfo> {

    private Activity mActivity;
    private Context mContext;
    RecyclerView mRecyclerView;
    private List<RoomInfo> mRoomInfoList;

    private final int itemCount = 16;

    public RvRoomAdapter(RecyclerView recyclerView, List<RoomInfo> roomInfoList, Activity activity) {
        super(recyclerView);
        mRecyclerView = recyclerView;
        mRoomInfoList = roomInfoList;
        mActivity = activity;
    }


    View tempV;

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, RoomInfo roomInfo) {
        mContext = helper.getConvertView().getContext();
        tempV = helper.getConvertView();
        Log.i(TAG, "fillData: " + roomInfo.getDirName());
        roomInfo.getResources().get(0).getResourceSysName();
        roomInfo.getDirName();

        ViewGroup desk = ((ViewGroup) helper.getConvertView());
        boolean isFillRow = desk.getChildCount() > 1;   //是否为最后填充的行
//        int t = position;
        TableLayout tl;
        Log.i(TAG, "fillData:childcount " + desk.getChildCount());
        for (int i = 0; i < desk.getChildCount(); i++) {
            if (isFillRow) {
                tl = (TableLayout) ((ViewGroup) (desk.getChildAt(i))).getChildAt(0);
            } else {
                tl = (TableLayout) (desk.getChildAt(0));
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
                    mTvSeatName.setEnabled(!(seat.getSeatStatus()==21));
                    mTvSeatName.setTag(seat);
                    mTvSeatName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tempV.setEnabled(true);
                            tempV = view;
                            view.setEnabled(false);
                            TextView roomSeat = mActivity.findViewById(R.id.tv_room_seat);
                            Button okSeat = mActivity.findViewById  (R.id.btn_ok_seat);
                            okSeat.setEnabled(true);
                            okSeat.setText("确认选座");
                            RoomInfo.Seat seat1 = (RoomInfo.Seat) mTvSeatName.getTag();
                            roomSeat.setTag(seat1.getId());
                            Toast.makeText(mContext, String.valueOf(seat1.getId()), Toast.LENGTH_SHORT).show();
                            roomSeat.setText(seat1.getResourceDisplayName());
                        }
                    });
                }
            }
        }
    }


    @Override
    public int getItemCount() {
//        return mRoomInfoList.size() - (mRoomInfoList.size() % 5) + 1;     //16
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < itemCount - 1) {
            return R.layout.item_desk_four;
        } else
            return R.layout.item_desk_fill;
    }

}
