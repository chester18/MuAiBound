package com.maternity.muaiwork.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.paper.PaperActivity;
import com.maternity.muaiwork.activity.user.CompanyActivity;
import com.maternity.muaiwork.activity.user.DetailUserInfoActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by apple on 2017/5/2.
 */
@ContentView(R.layout.fragment_me_unregist)
public class PageMyUnregist extends BaseFragment {
    boolean isNewAsk;
    String trueName;
    boolean hasPaper;
    public PageMyUnregist() {
        super();
    }

//    public PageMyUnregist(SendCommandInterface callback) {
//        super(callback);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= x.view().inject(this, inflater, container);
        return v;

    }
    //填写基本信息
    @Event(value = R.id.frag_4_acce_input_But,type = View.OnClickListener.class)
    private void inputInfo(View v)
    {
        Intent intent=new Intent(getActivity(), DetailUserInfoActivity.class);
        startActivity(intent);

    }
    //问卷
    @Event(value = R.id.frag_4_acce_paper,type = View.OnClickListener.class)
    private void startPaper(View v)
    {
        isNewAsk=true;
        hasPaper=false;
        if (isNewAsk) {

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
        }else {
            showToast("你目前不能答题，请完善个人信息！");
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
    private void startWork(View v)
    {
        if (isNewAsk&&hasPaper)
            activityCallback.sendCommand("page1");
        else {
            if (!hasPaper) {

                showToast("你未参加初试！");
                return;
            }
            if (!isNewAsk)
            {
                showToast("你未参加过培训！");
                return;
            }
        }
    }
}
