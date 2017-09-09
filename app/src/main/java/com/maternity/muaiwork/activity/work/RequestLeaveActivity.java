package com.maternity.muaiwork.activity.work;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.ApplyLeaveModel;
import com.maternity.muaiwork.view.LeaveHistoryCell;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/6/26.
 * 请假历史
 */
@ContentView(R.layout.activity_leave_history)
public class RequestLeaveActivity extends CBaseActivity {
    @ViewInject(R.id.leave_history_list)
    ListView historyListView;
    int page=1;
    int size=20;
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return historyModels.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ApplyLeaveModel model=historyModels.get(position);
            LeaveHistoryCell cell=new LeaveHistoryCell(RequestLeaveActivity.this);
            cell.setModel(model);
            return cell;
        }
    };
    List<ApplyLeaveModel> historyModels=new ArrayList<ApplyLeaveModel>();
    @ViewInject(R.id.leave_history_view)
    XRefreshView refreshView;
    int refreshStatus=0;
    public static long lastRefreshTime=0;
    boolean isLastPage=false;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        historyListView.setAdapter(baseAdapter);
        initData();
        initRefresh();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData()
    {
        JsonObject jo=new JsonObject();
        jo.addProperty("userId", PubFunction.userid);
        jo.addProperty("pageNumber",page);
        jo.addProperty("pageSize",size);
        sendPost(CommonNetString.leaveList,jo,"读取请假记录...",100);
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

                        historyModels.clear();
                        page=1;
                        initData();
                        refreshView.stopRefresh();          //停止下拉刷新UI
                        lastRefreshTime = refreshView.getLastRefreshTime();
                    }
                }, 1000);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                initData();
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
    @Event(value = R.id.leave_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }

    @Event(value = R.id.leave_new,type = View.OnClickListener.class)
    private void applyLeave(View v)
    {
        Intent intent=new Intent(RequestLeaveActivity.this,ApplyLeaveActivity.class);
        startActivityForResult(intent,10);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        historyModels.clear();
        page=1;

        initData();
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        JsonObject object= new JsonParser().parse(result).getAsJsonObject();
//        showToast(object.get("msg").toString());
        JsonArray array=object.getAsJsonArray("data");
        for (JsonElement jo :array)
        {
            historyModels.add(new ApplyLeaveModel(jo.getAsJsonObject()));
        }
        baseAdapter.notifyDataSetChanged();

    }
}
