package com.maternity.muaiwork.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.user.UserLoginActivity;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.common.DEF;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/1/22.
 */
@ContentView(R.layout.fragment_main_forth)
public class PageMainForth extends BaseFragment {
    @ViewInject(R.id.frag_4_acc_name)
    TextView me;
    boolean isNewAsk;
    String trueName;
    boolean hasPaper;
    @ViewInject(R.id.my_subFragment)
    LinearLayout subContent;
//    String[]icos={"{ion-ios-personadd-outline}",
//            "{ion-ios-people-outline}",
//            "{ion-ios-bookmarks-outline}",
//            "{ion-ios-compose-outline}",
//            "{ion-ios-briefcase-outline}",
//            "{ion-ios-medkit-outline}",
//            "{ion-ios-gear-outline}",
//            "{ion-ios-flower-outline}"
//    };
//    String[] items={"基本信息","家庭成员","获得证书","培训经历","体检记录","个人技能","服务经历","我的试卷"};

    public PageMainForth() {
        super();
    }

//    public PageMainForth(boolean hasPaper) {
//        this.hasPaper = hasPaper;
//    }


    public void setHasPaper(boolean hasPaper) {
        this.hasPaper = hasPaper;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= x.view().inject(this, inflater, container);
        isNewAsk=false;
//        JsonObject jo=new JsonObject();
//        jo.addProperty("userid", PubFunction.userid);
//        sendPost(CommonNetString.userAccountConfig,jo,"读取账户信息...",100);
        me.setText(PubFunction.userName);
        int utype=PubFunction.useType;
        if (utype== DEF.USER_MATERNITY)
        {
            changeFragment(true);
        }else {
            changeFragment(false);
        }
        return v;
    }

    @Event(value = R.id.frag_4_submitBtn,type = View.OnClickListener.class)
    private void clickYS(View v)
    {
        PubFunction.cleanInfo(getActivity());
        Intent intent=new Intent(getActivity(), UserLoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
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

    private void changeFragment(boolean isMaternity)
    {
        FragmentManager fm=this.getChildFragmentManager();
        FragmentTransaction ftra=fm.beginTransaction();
        if (isMaternity)
        {
            ftra.replace(R.id.my_subFragment,new PageMainForthMe()).commit();


        }else {
            ftra.replace(R.id.my_subFragment,new PageMainForthPaper()).commit();

        }
    }


}
