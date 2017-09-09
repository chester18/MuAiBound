package com.maternity.muaiwork.fragment;

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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.work.WorkHistoryActivity;
import com.maternity.muaiwork.activity.work.WorkRecorderActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.BaobaoModel;
import com.maternity.muaiwork.model.MamaModel;
import com.maternity.muaiwork.model.WorkOrderModel;
import com.maternity.muaiwork.view.Checkbox3Cell;
import com.maternity.muaiwork.view.Text2Cell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 2017/1/22.
 */
@ContentView(R.layout.fragment_main_third)
public class PageMainThird extends BaseFragment {
    @ViewInject(R.id.third_mama)
    ListView mamaListv;
    @ViewInject(R.id.third_baobao)
    ListView baobaoListv;

    BaseAdapter mamaAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return 4;
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
            Text2Cell cell=new Text2Cell(getActivity());
            switch (i)
            {
                case 0:
                    cell.setTxtValue("妈妈姓名：",mModel.mamaname);
                    break;
                case 1:
                    cell.setTxtValue("分娩医院：",mModel.hospital);
                    break;
                case 2:
                    cell.setTxtValue("分娩方式：",mModel.mamaname);
                    break;
                case 3:
                    cell.setTxtValue("分娩时间：",mModel.mamaname);
                    break;

            }
            return cell;
        }
    };
    BaseAdapter baobaoAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return 4;
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
            Text2Cell cell=new Text2Cell(getActivity());
            switch (i)
            {
                case 0:
                    cell.setTxtValue("宝宝姓名：",bModel.baoname);
                    break;
                case 1:
                    cell.setTxtValue("出生日期：",bModel.outdate);
                    break;
                case 2:
                        cell.setTxtValue("出生体重：",bModel.weight);
                    break;
                case 3:
                    Checkbox3Cell box=new Checkbox3Cell(getActivity());
                    box.setModel(bModel);
                    return box;
                    

            }
            return cell;
        }
    };
    int workOrderId=0;

    public PageMainThird() {
        super();
    }


    MamaModel mModel=new MamaModel();
    BaobaoModel bModel=new BaobaoModel();
    WorkOrderModel workOrderModel;
    List<BaobaoModel> bbList=new ArrayList<BaobaoModel>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= x.view().inject(this, inflater, container);
        mamaListv.setAdapter(mamaAdapter);
        baobaoListv.setAdapter(baobaoAdapter);
        loadData();
        return v;
    }


    //900001妈妈
    //900002宝宝
    @Event(value = R.id.mama_work,type = View.OnClickListener.class)
    private void mamaWork(View v)
    {
        if(PubFunction.workOrderId==0)
        {
            showToast("当前无上单的工单！");
            return;
        }
        Intent intent=new Intent(getActivity(), WorkRecorderActivity.class);
        intent.putExtra("workType",900001);
        intent.putExtra("serverId",mModel.mid);
        startActivity(intent);
    }
    @Event(value = R.id.mama_History,type = View.OnClickListener.class)
    private void mamaHistory(View v)
    {
        if(PubFunction.workOrderId==0)
        {
            showToast("当前无上单的工单！");
            return;
        }
        Intent intent=new Intent(getActivity(), WorkHistoryActivity.class);
        intent.putExtra("workType",900001);
        intent.putExtra("serverId",mModel.mid);
        startActivity(intent);
    }
    @Event(value = R.id.bao_work,type = View.OnClickListener.class)
    private void baoWork(View v)
    {
        if(PubFunction.workOrderId==0)
        {
            showToast("当前无上单的工单！");
            return;
        }
        Intent intent=new Intent(getActivity(), WorkRecorderActivity.class);
        intent.putExtra("workType",900002);
        intent.putExtra("serverId",bModel.bId);
        startActivity(intent);
    }
    @Event(value = R.id.bao_History ,type = View.OnClickListener.class)
    private void baoHistory(View v)
    {
        if(PubFunction.workOrderId==0)
        {
            showToast("当前无上单的工单！");
            return;
        }
        Intent intent=new Intent(getActivity(), WorkHistoryActivity.class);
        intent.putExtra("workType",900002);
        intent.putExtra("serverId",bModel.bId);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadData()
    {
        if (PubFunction.userid>0)
        {
            JsonObject jo=new JsonObject();
            jo.addProperty("workId",PubFunction.userid);
            sendPost(CommonNetString.getAllWorkOder,jo,"读取工单信息...",100);
        }


    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100)
        {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            JsonObject data=root.getAsJsonObject("data");
            mModel=new MamaModel(data.get("mominfo").getAsJsonObject());
            JsonArray listb=data.getAsJsonArray("childInfos");
            for (int i=0;i<listb.size();i++) {
                bbList.add(new BaobaoModel(listb.get(i).getAsJsonObject()));
            }
            if (bbList.size()>0)
            {
                bModel=bbList.get(0);
            }
            workOrderModel=new WorkOrderModel(data.get("workorder").getAsJsonObject());
            if (workOrderModel!=null)
            {
                workOrderId=workOrderModel.pid;
                PubFunction.workOrderId=workOrderModel.pid;
            }
            baobaoAdapter.notifyDataSetChanged();
            mamaAdapter.notifyDataSetChanged();


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void reflashFragment() {
        super.reflashFragment();
        loadData();
    }
}
