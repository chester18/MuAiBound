package com.maternity.muaiwork.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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
    @ViewInject(R.id.frag_1_list)
    ListView orderList;
    @ViewInject(R.id.adv_Image)
    ImageView advImage;
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
        needs=new ArrayList<>();
//        needs.add(new NeedInfoModel());
        orderList.setAdapter(orderAdaper);
        JsonObject ojb=new JsonObject();
        ojb.addProperty("pageNumber",1);
        ojb.addProperty("pageSize",20);
        x.image().bind(advImage,CommonNetString.advUrl);
        sendPost(CommonNetString.getOrderList,ojb,"信息读取中...",100);
        return v;
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
        try {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            JsonArray needlist=root.getAsJsonArray("data");
            for (int i=0;i<needlist.size();i++)
                needs.add(new NeedInfoModel(needlist.get(i).getAsJsonObject()));
            orderAdaper.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void reflashFragment() {
        super.reflashFragment();
        needs=new ArrayList<NeedInfoModel>();
        JsonObject ojb=new JsonObject();
        ojb.addProperty("pageNumber",1);
        ojb.addProperty("pageSize",20);
        x.image().bind(advImage,CommonNetString.advUrl);
        sendPost(CommonNetString.getOrderList,ojb,"信息读取中...",100);
    }
}
