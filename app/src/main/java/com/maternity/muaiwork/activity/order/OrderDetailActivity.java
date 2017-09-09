package com.maternity.muaiwork.activity.order;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.NeedInfoModel;
import com.maternity.muaiwork.view.BaseCell;
import com.maternity.muaiwork.view.DetailCell;
import com.maternity.muaiwork.view.MoreDetailCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/2/23.
 */
@ContentView(R.layout.activity_order_detail)
public class OrderDetailActivity extends CBaseActivity {
    @ViewInject(R.id.order_detail_list)
    ListView infoList;
    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return 8;
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
            BaseCell cell=null;
            if (needInfoModel!=null)
            switch (i)
            {
                case 0:
                    DetailCellModel cm=new DetailCellModel(null,null,"到手价","￥"+needInfoModel.price);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm);
                    break;
                case 1:
                    DetailCellModel cm1=new DetailCellModel(null,null,"奖金",needInfoModel.serverBonus);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm1);
                    break;
                case 2:
                    DetailCellModel cm2=new DetailCellModel(null,null,"预产期",needInfoModel.dueDate);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm2);
                    break;
                case 3:
                    DetailCellModel cm3=new DetailCellModel(null,null,"服务地区",needInfoModel.city);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm3);
                    break;
                case 4:
                    DetailCellModel cm4=new DetailCellModel(null,null,"服务周期",needInfoModel.days);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm4);
                    break;
                case 5:
                    DetailCellModel cm5=new DetailCellModel(null,null,"等级",needInfoModel.grade);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm5);
                    break;

                case 6:
                    DetailCellModel cm6=new DetailCellModel(null,null,"语言",needInfoModel.language);
                    cell=new DetailCell(OrderDetailActivity.this);
                    ((DetailCell) cell).setModel(cm6);
                    break;
                case 7:
                    MoreDetailCell md=new MoreDetailCell(OrderDetailActivity.this);
                    md.getViewHodel().mTitle.setText("其他要求：");
                    md.getViewHodel().mDetail.setText(needInfoModel.customNeed);
                    cell=md;
                    break;
                    default:
                        break;

            }
            return cell;
        }
    };

    NeedInfoModel needInfoModel;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        x.view().inject(this);
        needInfoModel= (NeedInfoModel) getIntent().getSerializableExtra("data");
        infoList.setAdapter(adapter);
    }
    @Event(value = R.id.order_detail_backICO,type = View.OnClickListener.class)
    private void backview(View v)
    {
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.order_detail_get,type = View.OnClickListener.class)
    private void getOrder(View v)
    {
        // 抢单
        int needid=needInfoModel.pid;
        int userid= PubFunction.userid;
        JsonObject obj=new JsonObject();
        obj.addProperty("userId",userid);
        obj.addProperty("needId",needid);
        sendPost(CommonNetString.applyOrderNeeds,obj,"抢单中......",100);

    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        JsonObject root=new JsonParser().parse(result).getAsJsonObject();
        if (root!=null)
        {
            int jg=root.get("result").getAsInt();
            if (jg>0)
            {
                finish();
            }else {
                showToast(root.get("msg").getAsString());
            }

        }
    }
}
