package com.maternity.muaiwork.activity.user;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.fragment.BaseFragment;
import com.maternity.muaiwork.fragment.PageMainFirst;
import com.maternity.muaiwork.fragment.PageMainForth;
import com.maternity.muaiwork.fragment.PageMainLogin;
import com.maternity.muaiwork.fragment.PageMainSecond;
import com.maternity.muaiwork.fragment.PageMainThird;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/1/22.
 */
@ContentView(R.layout.activity_tab_main_page)
public class MuAiMainTabActivity extends CBaseActivity implements BaseFragment.SendCommandInterface {

    IndicatorViewPager indicatorViewPager;
    FixedIndicatorView indicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main_page);
        SViewPager sViewPager= (SViewPager) findViewById(R.id.tabmain_viewPager);
        indicator= (FixedIndicatorView) findViewById(R.id.tabmain_indicator);
        int select=ContextCompat.getColor(this,R.color.font_select);
        int unselect=ContextCompat.getColor(this,R.color.font_unselect);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(select,unselect));
        indicatorViewPager=new IndicatorViewPager(indicator,sViewPager);
        indicatorViewPager.setAdapter(new PageMyAdapter(getSupportFragmentManager()));
//        indicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
//            @Override
//            public void onIndicatorPageChange(int preItem, int currentItem) {
//                PageMyAdapter myAdapter= (PageMyAdapter) indicatorViewPager.getAdapter();
//                BaseFragment fragment= (BaseFragment) myAdapter.getFragmentForPage(currentItem);
//                fragment.reflashFragment();
//            }
//        });
        sViewPager.setCanScroll(false);
        sViewPager.setOffscreenPageLimit(4);

    }

    @Override
    public void sendCommand(String msg) {
        if (msg.equals("page1"))
        {
            indicator.setCurrentItem(1,true);
        }else if (msg.equals("page2"))
        {
            indicator.setCurrentItem(2,true);
        }else if (msg.equals("page3"))
        {
            indicator.setCurrentItem(3,true);
        }else if (msg.equals("page4"))
        {
            indicator.setCurrentItem(4,true);
        }
    }

    private class PageMyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
        String[] tabNames={"抢单","学习","记录","我"};
        int[] tabIcons={R.drawable.main_tab1,R.drawable.main_tab2,R.drawable.main_tab3,R.drawable.main_tab4};
        LayoutInflater inflater;
        public PageMyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater=LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.view_tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0,tabIcons[position],0,0);
            return textView;
        }

        @Override
        public android.support.v4.app.Fragment getFragmentForPage(int position) {
            BaseFragment f;
            switch (position)
            {
                case 0:
                    f=new PageMainFirst();
                    break;
                case 1:
                    f=new PageMainSecond();
                    break;
                case 2:
                    f=new PageMainThird();
                    break;
                case 3:
                    f= PubFunction.userid>0?new PageMainForth(): new PageMainLogin(); //new PageMainForth();
                    break;
                default:
                    f=new BaseFragment();
                    break;
            }
            f.setActivityCallback(MuAiMainTabActivity.this);
            return f;
        }
    }
}
