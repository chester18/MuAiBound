package com.maternity.muaiwork.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;

import com.maternity.muaiwork.activity.order.NeedDetailActivity;
import com.maternity.muaiwork.activity.order.OrderDetailActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.NeedInfoModel;
import com.maternity.muaiwork.view.DueInfoCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/1/22.
 */
@ContentView(R.layout.fragment_main_first)
public class PageMainFirst extends BaseFragment {
    @ViewInject(R.id.headRefresh)
    XRefreshView refreshView;
    @ViewInject(R.id.frag_1_list)
    ListView orderList;
    @ViewInject(R.id.adv_Image)
    ImageView advImage;
    public static long lastRefreshTime=0;
    boolean isLastPage=false;
    int refreshStatus=0;//0:首次载入，1下拉刷新，2上拉更多
    int type=0;
    int size=20;
    int page=1;
    BaseAdapter orderAdaper=new BaseAdapter() {
        @Override
        public int getCount() {
            return needs.size();
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
            DueInfoCell cell=new DueInfoCell(getActivity());
            cell.setModel(needs.get(i));
            return cell;
        }
    };
    List<NeedInfoModel> needs;
    public PageMainFirst() {
        super();
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= x.view().inject(this, inflater, container);
        initRef();
        needs=new ArrayList<>();
//        needs.add(new NeedInfoModel());
        orderList.setAdapter(orderAdaper);

        x.image().bind(advImage,CommonNetString.advUrl);


        return v;
    }

    private void initRef() {
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

                        needs.clear();
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadData()
    {
        JsonObject ojb=new JsonObject();
        ojb.addProperty("pageNumber",page);
        ojb.addProperty("pageSize",size);
        sendPost(CommonNetString.getOrderList,ojb,"信息读取中...",100);
    }

    //    @Event(value = R.id.frag_1_mydate,type = View.OnClickListener.class)
//    private void mydateClick(View v)        //我的档期
//    {
////        showToast("我的档期");
//        Intent intent=new Intent(getActivity(), MyScheduleActivity.class);
//        getActivity().startActivity(intent);
//    }
//
//    @Event(value = R.id.frag_1_myred,type = View.OnClickListener.class)
//    private void myredClick(View v)  //我的红包
//    {
//        Intent intent=new Intent(getActivity(), MyRedpackageActivity.class);
//        getActivity().startActivity(intent);
////        showToast("我的红包");
//    }
    @Event(value = R.id.frag_1_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
//        showToast("cc");
        if(PubFunction.useType!=100002)
        {
            activityCallback.sendCommand("page3");
            showToast("请先完善个人信息！");
            return;
        }

        Intent intent=new Intent(getActivity(), NeedDetailActivity.class);
        intent.putExtra("data",needs.get(position));
//        startActivity(intent);
        getActivity().startActivity(intent);
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100) {
            try {
                JsonObject root = new JsonParser().parse(result).getAsJsonObject();
                JsonArray needlist = root.getAsJsonArray("data");
                for (int i = 0; i < needlist.size(); i++)
                    needs.add(new NeedInfoModel(needlist.get(i).getAsJsonObject()));
                orderAdaper.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void reflashFragment() {
        super.reflashFragment();
        needs=new ArrayList<NeedInfoModel>();

        x.image().bind(advImage,CommonNetString.advUrl);
        needs.clear();
        page=1;
        loadData();
    }
}
