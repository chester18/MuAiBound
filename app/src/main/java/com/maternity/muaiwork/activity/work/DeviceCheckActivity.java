package com.maternity.muaiwork.activity.work;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.DeviceModel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/7/6.
 */
@ContentView(R.layout.activity_device_checked)
public class DeviceCheckActivity extends CBaseActivity {
    @ViewInject(R.id.dev_title)
    TextView titleTextView;
    @ViewInject(R.id.dev_list)
    ListView deviceListView;
    @ViewInject(R.id.dev_select_all)
    Button selectAllBtn;
    int pageNumber=1;
    int size=30;
    int deviceType; //900002领用  900005归还 900007移交
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return models.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            CheckBox cb=new CheckBox(DeviceCheckActivity.this);
            final DeviceModel model=models.get(position);
            if (mySelecteds.contains(model.sid))
                cb.setChecked(true);
            else cb.setChecked(false);
            cb.setText(model.devName);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DeviceModel dev=models.get(position);
                    if (!isChecked)
                    {
                        for (int t=0;t<mySelecteds.size();t++)
                        {
                            if (dev.sid==mySelecteds.get(t))
                            {
                                mySelecteds.remove(t);
                            }
                        }

                    }else {
                        mySelecteds.add(dev.sid);
                    }
                    baseAdapter.notifyDataSetChanged();
                }
            });
            return cb;
        }
    };
    List<DeviceModel> models=new ArrayList<DeviceModel>();
    boolean isSelectAll=false;
    List<Integer> mySelecteds=new ArrayList<Integer>();
    int orderId=0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        deviceListView.setAdapter(baseAdapter);
        initData();
        deviceType=getIntent().getIntExtra("type",900009);
        orderId=getIntent().getIntExtra("orderId",0);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData()
    {
        //获取设备信息
        JsonObject json=new JsonObject();
        json.addProperty("pageNumber",pageNumber);
        json.addProperty("pageSize",size);
        sendPost(CommonNetString.getToolList,json,"读取设备清单...",100);

    }
    @Event(value = R.id.dev_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
    //全选
    @Event(value = R.id.dev_select_all,type = View.OnClickListener.class)
    private void selectAll(View v)
    {
        isSelectAll=!isSelectAll;
        if (!isSelectAll)
        {
            isSelectAll=false;
            selectAllBtn.setText("全选");
            mySelecteds.clear();
            baseAdapter.notifyDataSetChanged();
        }else {
            isSelectAll=true;
            selectAllBtn.setText("清空");
            mySelecteds.clear();
            for (DeviceModel deviceModel:models)
            {
                mySelecteds.add(deviceModel.sid);
            }
            baseAdapter.notifyDataSetChanged();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.dev_submit,type = View.OnClickListener.class)
    private void submitChecked(View v)
    {
        //提交信息
        if (mySelecteds.size()==0)
        {
            showToast("请选择领用设备！");
            return;
        }
        String str="";
        for (int i=0;i<mySelecteds.size();i++)
        {
            if (i==mySelecteds.size()-1)
            {
                str=str+mySelecteds.get(i).toString();
            }else {
                str=str+mySelecteds.get(i).toString()+",";
            }
        }
        JsonObject jo=new JsonObject();
        jo.addProperty("workId", PubFunction.userid);
        jo.addProperty("toolId",str);
        jo.addProperty("workorderId",orderId);
        sendPost(CommonNetString.insertTool,jo,"提交设备确认...",200);


    }
    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100)
        {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            JsonArray dev=root.get("data").getAsJsonArray();
            if (dev!=null)
            {
                for (int i=0;i<dev.size();i++)
                {
                    models.add(new DeviceModel(dev.get(i).getAsJsonObject()));
                }
                baseAdapter.notifyDataSetChanged();
            }
        }
        if (tag==200)
        {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            if (root.get("result").getAsInt()>0)
            {
                showToast("提交完成");
                finish();
            }

        }
    }
}
