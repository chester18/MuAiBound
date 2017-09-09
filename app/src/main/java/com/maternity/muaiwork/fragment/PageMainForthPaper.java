package com.maternity.muaiwork.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.paper.PaperActivity;
import com.maternity.muaiwork.activity.user.CompanyActivity;
import com.maternity.muaiwork.activity.user.DetailUserInfoActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/6/19.
 */
@ContentView(R.layout.fragment_main_forth_paper)
public class PageMainForthPaper extends BaseFragment {
    @ViewInject(R.id.step_1)
    private Button step1;
    @ViewInject(R.id.step_2)
    private Button step2;
    @ViewInject(R.id.step_3)
    private Button step3;
    @ViewInject(R.id.step_4)
    private Button step4;

    boolean hasInfo=false;
    boolean hasPaper=false;

    public PageMainForthPaper() {
        super();
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1) {
                JsonObject data=jo.getAsJsonObject("data");
                hasInfo=data.get("hasInfo").getAsBoolean();
//                if (isNewAsk)
//                {
//                    trueName=data.get("name").getAsString();
//                }
                hasPaper=data.get("hasPaper").getAsBoolean();
                changeSuccess();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= x.view().inject(this, inflater, container);
        changeSuccess();
        JsonObject jo=new JsonObject();
        jo.addProperty("userid", PubFunction.userid);
        sendPost(CommonNetString.userAccountConfig,jo,"读取账户信息...",100);

        return v;
    }

    private void changeSuccess()
    {
        if (hasInfo) step1.setTextColor(Color.GREEN);
        if (hasPaper) step2.setTextColor(Color.GREEN);
    }

    ////////////////////////////////////////////////////////////
    //信息输入
    @Event(value = R.id.step_1,type = View.OnClickListener.class)
    private void inputInfo(View v)
    {
        if (hasInfo)
        {
            showToast("您的信息已经填写完成！");
        }else {
            Intent intent = new Intent(getActivity(), DetailUserInfoActivity.class);
            intent.putExtra("isEdit",true);
            startActivity(intent);
        }

    }
    //问卷
    @Event(value = R.id.step_2,type = View.OnClickListener.class)
    private void startPaper(View v)
    {


            if (hasPaper)
            {
                showToast("您已经答过题，可以直接参加面试！面试地址请点击“第三步！”");

            }else {
                Intent intent = new Intent(getActivity(), PaperActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("paperId", 1);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }

    }
    //面试地
    @Event(value = R.id.frag_4_acce_face,type = View.OnClickListener.class)
    private void faceInfo(View v)
    {
        Intent intent=new Intent(getActivity(),CompanyActivity.class);
        startActivity(intent);
    }
    //开始接单
    @Event(value = R.id.frag_4_acce_start_work,type = View.OnClickListener.class)
    private void startWork(View v) {

            if (!hasPaper) {

                showToast("您未通过线上理论考试！");
                return;
            }
            if (!hasInfo) {
                showToast("你未完善个人基本信息！");
                return;
            }
//        }
    }
}
