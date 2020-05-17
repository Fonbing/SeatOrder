package com.lxt.seatorder.ui.adapter;

import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 * 作者：Created by 龙啸天 on 20191123
 * 邮箱：jxfengmtx@163.com ---17718
 * <p>
 * 承担viewPager与fragment的适配（界面的滑动切换）
 */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;


    public HomeFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return this.mFragmentList == null ? null : this.mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.mFragmentList == null ? 0 : this.mFragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("dynamic", "destroyItem: " + position);
        super.destroyItem(container, position, object);
    }

}
