package com.maternity.muaiwork.activity.user;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.activity.paper.PaperActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.dialog.BaseDialog;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.view.IcoCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 16/10/14.
 */
@ContentView(R.layout.activity_myaccount)
public class MyAccountActivity extends CBaseActivity {
    @ViewInject(R.id.acc_name)
    TextView me;
    boolean isNewAsk;
    String trueName;
    boolean hasPaper;

    String[]icos={"{ion-ios-personadd-outline}",
                  "{ion-ios-people-outline}",
                  "{ion-ios-bookmarks-outline}",
                  "{ion-ios-compose-outline}",
                  "{ion-ios-briefcase-outline}",
                  "{ion-ios-medkit-outline}",
                  "{ion-ios-gear-outline}",
                  "{ion-ios-flower-outline}"
            };
    String[] items={"基本信息","家庭成员","获得证书","培训经历","体检记录","个人技能","服务经历","我的试卷"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PubFunction.readInfo(this);
//        PubFunction.userid=100002;
        x.view().inject(this);
        isNewAsk=false;
        //PubFunction.userid=100007;
        //PubFunction.userid=100007;
        JsonObject jo=new JsonObject();
        jo.addProperty("userid",PubFunction.userid);
        sendPost(CommonNetString.userAccountConfig,jo,"读取账户信息...",100);
        me.setText(PubFunction.userName);
    }
    @Event(value = R.id.submitBtn,type = View.OnClickListener.class)
    private void clickYS(View v)
    {
        showToast("申请成为月嫂");
    }
    //信息输入
    @Event(value = R.id.acce_input_But,type = View.OnClickListener.class)
    private void inputInfo(View v)
    {
        Intent intent=new Intent(MyAccountActivity.this, DetailUserInfoActivity.class);

        startActivity(intent);

    }
    //问卷
    @Event(value = R.id.acce_paper,type = View.OnClickListener.class)
    private void startPaper(View v)
    {
        isNewAsk=true;
        hasPaper=false;
        if (isNewAsk) {

            if (hasPaper)
            {
                showToast("您已经答过题，可以直接参加面试！面试地址请点击“第三步！”");

            }else {
                Intent intent = new Intent(MyAccountActivity.this, PaperActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("paperId", 1);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        }else {
            showToast("你目前不能答题，请完善个人信息！");
        }
    }
    //面试地
    @Event(value = R.id.acce_face,type = View.OnClickListener.class)
    private void faceInfo(View v)
    {
        Intent intent=new Intent(MyAccountActivity.this,CompanyActivity.class);
        startActivity(intent);
    }
    //开始接单
    @Event(value = R.id.acce_start_work,type = View.OnClickListener.class)
    private void startWork(View v)
    {

    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1) {
                JsonObject data=jo.getAsJsonObject("data");
                isNewAsk=data.get("hasInfo").getAsBoolean();
                if (isNewAsk)
                {
                    trueName=data.get("name").getAsString();
                }
                hasPaper=data.get("hasPaper").getAsBoolean();
            }
            }
    }
}
