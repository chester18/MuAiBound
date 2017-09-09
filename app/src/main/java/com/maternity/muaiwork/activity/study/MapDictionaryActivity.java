package com.maternity.muaiwork.activity.study;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.TopicModel;
import com.maternity.muaiwork.view.DetailCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by apple on 2017/8/18.
 */
@ContentView(R.layout.activity_study_list)
public class MapDictionaryActivity extends CBaseActivity {
    public int width,height;
    @ViewInject(R.id.stud_custom_view)
    XRefreshView refreshView;
    @ViewInject(R.id.stud_tpoic_list)
    ListView topicList;
    @ViewInject(R.id.stud_title_view)
    TextView tvTitle;
    public static long lastRefreshTime=0;
    boolean isLastPage=false;
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return keys.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            DetailCell cell=new DetailCell(MapDictionaryActivity.this);
            cell.setModel(new DetailCellModel(null,keys.get(i),dictionary.get(keys.get(i)),null));
            cell.setLayoutParams(new AbsListView.LayoutParams((int) (width*1.5), -2));
            return cell;
        }
    };
    int refreshStatus=0;//0:首次载入，1下拉刷新，2上拉更多
    int type=0;
    int size=20;
    int page=1;
    Map<String,String> dictionary=new HashMap<>();
    List<String> keys=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        //getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;   //getDefaultDisplay().getWidth();
        height = metrics.heightPixels;
        x.view().inject(this);
        dictionary=(Map<String, String>) getIntent().getSerializableExtra("data");
        if (dictionary!=null)
        {

            for(String str : dictionary.keySet())
            {
                keys.add(str);
            }
        }
        topicList.setAdapter(baseAdapter);
        initRefresh();
//        Intent intent=getIntent();

    }
    private void initRefresh()
    {
        refreshView.restoreLastRefreshTime(lastRefreshTime);
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {


            @Override
            public void onRefresh() {

            }

            @Override
            public void onRefresh(boolean isPullDown) {
                refreshView.stopRefresh();
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLoadMore(boolean isSilence) {
                refreshView.stopLoadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
        refreshView.setPullRefreshEnable(false);
        refreshView.setPullLoadEnable(false);
    }
    /**
     * 返回按钮
     * @param v
     */
    @Event(value = R.id.stud_detail_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
}
