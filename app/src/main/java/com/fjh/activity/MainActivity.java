package com.fjh.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fjh.adapter.FragmentViewAdapter;
import com.fjh.fragment.Fragment1;
import com.fjh.fragment.Fragment2;
import com.fjh.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjh on 2016/3/28.
 */

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout; //定义tablayout
    private ViewPager mViewPager; //定义viewpager
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main); //给当前activity引入的布局文件

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_28);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationview);

        initViewPager();


        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    private MenuItem mMenuItem;
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (mMenuItem != null) mMenuItem.setChecked(false);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mMenuItem = menuItem;
                        return true;
                    }
                }
        );

//
//        String[] dataset = new String[100];
//        for (int i = 0; i < dataset.length; i++) {
//            dataset[i] = "item" + i;
//        }
//
//        //可以放进去 但格式不对
//        fragment1 = new Fragment1();
//        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment1).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager(){

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        List<Fragment> fragments; //fragment数组
        List<String> tabTitle; //tab的title

        /*
        设置tab的名字，放置为一个数组
        */
        tabTitle = new ArrayList<>();
        tabTitle.add("首页");
        tabTitle.add("预订");
        tabTitle.add("商城");

        /*
        为tablayout添加tab的名称
        */
        for (int i = 0; i< tabTitle.size(); i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabTitle.get(i)));
        }

        /*
        添加viewpager与fragment
        将fragment装进List数组中
        */
        fragments=new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());

        //构造适配器
        FragmentViewAdapter adapter = new FragmentViewAdapter(getSupportFragmentManager(), fragments, tabTitle);
        mViewPager.setAdapter(adapter); //给viewpager设置适配器
        tabLayout.setupWithViewPager(mViewPager); //将tablayout与viewpager关联
        tabLayout.setTabsFromPagerAdapter(adapter); //给tabs设置适配器

    }
}
