package com.maternity.muaiwork.activity.user;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * Created by apple on 16/10/13.
 */
@ContentView(R.layout.activity_userlogin)
public class UserLoginActivity extends CBaseActivity {
    @ViewInject(R.id.uname)
    EditText uname;
    @ViewInject(R.id.upass)
    EditText upass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.ulogin,type = View.OnClickListener.class)
    private void onlogin (View btn)
    {

        if (uname.getText().length()<6 ||upass.getText().length()<6)
        {
            showToast("用户名、密码必须大于6位");
            return;
        }
        JsonObject ojs=new JsonObject();
        ojs.addProperty("name",uname.getText().toString());
        ojs.addProperty("pass",upass.getText().toString());
        sendPost(CommonNetString.maLogin,ojs,"登录中...",1);
//        Intent intent =new Intent(this,MyAccountActivity.class);
//        startActivity(intent);
//        finish();

    }

//    @Event(value = R.id.button,type = View.OnClickListener.class)
//    private void onregist (View btn)
//    {
//        Intent intent=new Intent(this,UserRegistActivity.class);
//        startActivity(intent);
//
//    }

    @Override
    protected void getResult(String result, int tag) {
        JsonObject object= new JsonParser().parse(result).getAsJsonObject();
//        showToast(object.get("msg").toString());
        JsonObject user=object.getAsJsonObject("data");
        PubFunction.userid=user.get("id").getAsInt();
        PubFunction.userName=user.get("userName").getAsString();
        PubFunction.useType=user.get("typeId").getAsInt();
        PubFunction.saveInfo(this);
        if (object.get("result").getAsInt()==1)
        {
            Intent intent =new Intent(this,MuAiMainTabActivity.class);
            startActivity(intent);
            showToast(user.toString());
            finish();
        }else {
            showToast(object.get("msg").toString());
        }


    }
}
