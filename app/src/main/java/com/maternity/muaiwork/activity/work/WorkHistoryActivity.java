package com.maternity.muaiwork.activity.work;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.maternity.muaiwork.model.WorkHistoryModel;
import com.maternity.muaiwork.view.DetailCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/3/9.
 */
@ContentView(R.layout.activity_study_list)
public class WorkHistoryActivity extends CBaseActivity {
    @ViewInject(R.id.stud_title_view)
    TextView title;
    @ViewInject(R.id.stud_tpoic_list)
    ListView listView;
    @ViewInject(R.id.stud_custom_view)
    XRefreshView refreshView;
    public static long lastRefreshTime=0;
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return historyList.size();
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
            WorkHistoryModel model=historyList.get(i);
            DetailCellModel detail=new DetailCellModel(null,model.workRecordItemName,model.workRecordListValue,model.workRecordListDate,null);
            DetailCell cell=new DetailCell(WorkHistoryActivity.this);
            cell.setModel(detail);
            cell.setLayoutParams(new AbsListView.LayoutParams((int) (screenWidth*1.5),-2));
            return cell;
        }
    };
    List<WorkHistoryModel> historyList=new ArrayList<WorkHistoryModel>();
    int page=1;
    int size=20;
    int status=0; //1=下拉，2=上拉；0初始化
    int workType=0;//记录类型
    int orderId=PubFunction.workOrderId;
    int serverId;//服务对象ID
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        workType=getIntent().getIntExtra("workType",0);
//        orderId=getIntent().getIntExtra("orderId",0);
        serverId=getIntent().getIntExtra("serverId",0);
        String stitle="历史记录";
        if (workType==900001) stitle="妈妈历史记录";
        if (workType==900002) stitle="宝宝历史记录";
        title.setText(stitle);
        listView.setAdapter(baseAdapter);
        loadData();
        initRefresh();

    }

    
    @Event(value = R.id.stud_detail_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
    private void initRefresh()
    {
        refreshView.setPullLoadEnable(true);
        refreshView.restoreLastRefreshTime(lastRefreshTime);
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {


            @Override
            public void onRefresh() {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onRefresh(boolean isPullDown) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refreshView.stopRefresh();          //停止下拉刷新UI
//                        lastRefreshTime = refreshView.getLastRefreshTime();
//                    }
//                }, 5000);
                historyList.clear();
                status=1;
                loadData();

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLoadMore(boolean isSilence) {
                //
                status=2;
                loadData();

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadData()
    {
        JsonObject jo=new JsonObject();
        jo.addProperty("pageNumber",page);
        jo.addProperty("pageSize",size);
        jo.addProperty("workOrderId",orderId);
        jo.addProperty("workerId", PubFunction.userid);
        jo.addProperty("serverId",serverId);
        String url="";
        if (workType==900001)
            url=CommonNetString.selectHistoryMom;
        else url=CommonNetString.selectHistoryChild;
        sendPost(url,jo,"获取工作记录...",100);
    }

    @Override
    protected void getResult(String result, int tag) {
        if (tag==100)
        {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            int resulta=root.get("result").getAsInt();
            if (resulta>0)
            {
                JsonArray array=root.getAsJsonArray("data");
                for (int i=0;i<array.size();i++)
                {
                    historyList.add(new WorkHistoryModel(array.get(i).getAsJsonObject()));
                }
            }
            baseAdapter.notifyDataSetChanged();
            if (status==1) refreshView.stopRefresh();
            if (status==2) refreshView.stopLoadMore();
        }
    }
}
