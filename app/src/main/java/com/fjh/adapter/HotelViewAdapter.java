package com.fjh.adapter;

/**
 * 用于显示酒店详情的图片
 *  使用PagerAdapter来生成一个页面视图，也就是一页;
 *  PagerAdapter决定了ViewPager一共有多少页，负责每页的初始化，每页的销毁等工作
 * Created by fjh .
 */
//public class HotelViewAdapter extends PagerAdapter{
//
//    @Override
//    public int getCount() {
//        //设置数据长度为Integer.MAX_VALUE，这样就可以一直滚动
//        return Integer.MAX_VALUE;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        //若position超过mDataList.size()，会发生越界异常，所以这里每次超过size又从0开始计算位置
//        position = position % mDataList.size();
//        ImageView img = mDataList.get(position);
//        container.addView(img);
//
//        return img;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//
//        position = position % mDataList.size();
//        container.removeView((View)object);
//    }
//}
