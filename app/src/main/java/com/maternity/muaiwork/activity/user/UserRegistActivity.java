package com.maternity.muaiwork.activity.user;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by apple on 16/10/14.
 */
@ContentView(R.layout.activity_user_regist)
public class UserRegistActivity extends CBaseActivity {
    @ViewInject(R.id.reg_name)
    EditText rname;
    @ViewInject(R.id.reg_pass)
    EditText rpass;
    @ViewInject(R.id.reg_rrpass)
    EditText rrpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        短信初始化
        eventHandler=new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                super.afterEvent(i, i1, o);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.reg_ben,type = View.OnClickListener.class)
    private void regBtn(View btn)
    {
        if (rname.getText().toString().length()<6 ||rpass.getText().toString().length()<6)
        {
            showToast("用户名、密码必须大于6位!");
            return;
        }
        if (!rpass.getText().toString().equals(rrpass.getText().toString()))
        {
            showToast("两此输入密码不一至!");
            return;
        }
        JsonObject ojs=new JsonObject();
        ojs.addProperty("name",rname.getText().toString());
        ojs.addProperty("pass",rpass.getText().toString());
        sendPost(CommonNetString.maRegist,ojs,"注册中...",1);

    }
    @Override
    protected void getResult(String result, int tag) {
        JsonObject object= new JsonParser().parse(result).getAsJsonObject();
        finish();

    }
    @Event(value = R.id.reg_back,type = View.OnClickListener.class)
    private void back(View v)
    {
        finish();
    }

    EventHandler eventHandler;
    @Override
    protected void onDestroy() {

        SMSSDK.unregisterEventHandler(eventHandler);
        super.onDestroy();

    }
}
