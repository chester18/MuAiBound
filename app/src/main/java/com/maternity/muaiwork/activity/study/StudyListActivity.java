package com.maternity.muaiwork.activity.study;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.TopicModel;
import com.maternity.muaiwork.view.DetailCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.maternity.muaiwork.common.CommonNetString.getTopicList;
import static java.security.AccessController.getContext;

/**
 * Created by apple on 2017/2/28.
 */
@ContentView(R.layout.activity_study_list)
public class StudyListActivity extends CBaseActivity {
    /**
     * 700001月嫂
     * 700002育儿嫂
     * 700003妈妈
     */


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
            return dataList.size();
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
            Object boj=dataList.get(i);
//            if (boj.getClass().getName().equals("String"))
//            {
//                LayoutInflater inflater=getLayoutInflater();
//                view= inflater.inflate(R.layout.cell_title, viewGroup, false);
//                TextView textView = (TextView) view;
//                textView.setText((String)boj);
//                return textView;
//
//            }else {
                TopicModel tm=(TopicModel)boj;
                DetailCell cell=new DetailCell(StudyListActivity.this);
                cell.setModel(new DetailCellModel(null,null,tm.topicName,tm.topicDate));
                cell.setLayoutParams(new AbsListView.LayoutParams((int) (width*1.5), -2));
//                view=cell;
                return cell;
//            }
//            return null;
        }
    };
    List<Object> dataList=new ArrayList<Object>();
    int refreshStatus=0;//0:首次载入，1下拉刷新，2上拉更多
    int type=0;
    int size=20;
    int page=1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        //dataList.add(new String("我的文章"));

        //dataList.add(8,new String("HOOD"));
        topicList.setAdapter(baseAdapter);
        initRefresh();
        Intent intent=getIntent();
        type=intent.getIntExtra("ttype",700001);

        loadData();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadData()
    {
        JsonObject jo=new JsonObject();
        jo.addProperty("userId", PubFunction.userid);
        jo.addProperty("type",type);
        jo.addProperty("pageNumber",page);
        jo.addProperty("pageSize",size);
        sendPost(CommonNetString.getTopicList,jo,"读取文章列表...",100);
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
                new Handler().postDelayed(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {

                        dataList.clear();
                        page=1;
                        loadData();
//                        refreshView.stopRefresh();          //停止下拉刷新UI
//                        lastRefreshTime = refreshView.getLastRefreshTime();
                    }
                }, 1000);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                loadData();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
        refreshView.setPullRefreshEnable(true);
        refreshView.setPullLoadEnable(true);
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

    /**
     * 点击文章
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Event(value = R.id.stud_tpoic_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        Object obj=dataList.get(position);
        if (!obj.getClass().getName().equals("String"))
        {
            TopicModel tm=(TopicModel)obj;
            Intent intent=new Intent(StudyListActivity.this,WebViewActivity.class);
            intent.putExtra("url",tm.topicListUrl);
            startActivity(intent);
        }
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        JsonObject root=new JsonParser().parse(result).getAsJsonObject();
        JsonArray modeList=root.get("data").getAsJsonArray();
        if (modeList!=null)
        {
            for (int i=0;i<modeList.size();i++)
            {
                dataList.add(new TopicModel(modeList.get(i).getAsJsonObject()));
            }
            baseAdapter.notifyDataSetChanged();
        }
        if (modeList.size()<size)
        {
            refreshView.setPullLoadEnable(false);
        }
        if(refreshStatus==1)
        {
            refreshView.stopRefresh();          //停止下拉刷新UI
            lastRefreshTime = refreshView.getLastRefreshTime();
        }
        if (refreshStatus==2)
        {
            refreshView.stopLoadMore();
        }
    }
}
