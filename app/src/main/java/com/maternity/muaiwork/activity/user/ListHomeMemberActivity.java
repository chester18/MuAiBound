package com.maternity.muaiwork.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.HomeMemberModel;
import com.maternity.muaiwork.model.TrainingModel;
import com.maternity.muaiwork.view.IcoCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/10/14.
 */
@ContentView(R.layout.activity_nomal_list)
public class ListHomeMemberActivity extends CBaseActivity {
    @ViewInject(R.id.list_title)
    TextView title;
    @ViewInject(R.id.nomal_list)
    ListView lv;
    List<Object> dataList=new ArrayList<>();
    BaseAdapter adapter=new BaseAdapter() {
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
            HomeMemberModel model= (HomeMemberModel) dataList.get(i);
            IcoCell cell=new IcoCell(ListHomeMemberActivity.this);
            cell.setModel(new DetailCellModel(null,null,model.Relation,model.mobile,"{md-chevron-right}"));
            cell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT));
            cell.setBackgroundResource(R.color.CW);
            return cell;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        title.setText("家庭成员");
        lv.setAdapter(adapter);
        loadingData();
    }
    @Event(value = R.id.backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
    @Event(value = R.id.list_add,type = View.OnClickListener.class)
    private void addTraining(View v)
    {
        Intent intent=new Intent(this,DetailHomeMemberActivity.class);
        startActivity(intent);
    }
    @Event(value = R.id.nomal_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent=new Intent(this,DetailHomeMemberActivity.class);
        intent.putExtra("model",(HomeMemberModel)dataList.get(position));
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadingData();
    }

    private void loadingData()
    {
        JsonObject ojs=new JsonObject();
        ojs.addProperty("uid", PubFunction.userid);

        sendPost(CommonNetString.userReadMember,ojs,"读取家庭成员...",1);
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        dataList.clear();
        JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
        if (jo.get("result").getAsInt()==1)
        {
            JsonArray array=jo.getAsJsonArray("data");
            for (int i=0;i<array.size();i++)
            {
                dataList.add(new HomeMemberModel(array.get(i).getAsJsonObject()));
            }
            adapter.notifyDataSetChanged();
        }else {
            showToast("记录获取失败");
        }
    }
}
