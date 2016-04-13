package com.fjh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

  //专为viewpager的适配器

public class FragmentViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments; //fragment数组
    private List<String> mTitle; //tab名称数组

//  从构造函数所以看出，我们要构造Fragment的集合，所以我们先产生我们所需要的Fragment类

    /*
    对于构造函数，这里申请了一个Fragment的List对象，用于保存用于滑动的Fragment对象，并在创造函数中初始化
     */
    public FragmentViewAdapter(FragmentManager fm, List<Fragment> fragments, List<String> title) {
        super(fm);
        mFragments = fragments;
        mTitle = title;
    }

    /*
    在getItem(int arg0)中，根据传来的参数arg0，来返回当前要显示的fragment
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /*
    getCount()返回用于滑动的fragment总数
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    /*
    显示tab上的名字
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
