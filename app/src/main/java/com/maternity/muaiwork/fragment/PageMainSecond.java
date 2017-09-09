package com.maternity.muaiwork.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.study.StudyListActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by apple on 2017/1/22.
 */
@ContentView(R.layout.fragment_main_second)
public class PageMainSecond extends BaseFragment {

    public PageMainSecond() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= x.view().inject(this, inflater, container);


        return v;
    }
    @Event(value = R.id.frag_2_ysmsg,type = View.OnClickListener.class)
    private void ysmsg(View v)
    {
        Intent intent=new Intent(getActivity(), StudyListActivity.class);
        intent.putExtra("ttype",700001);
        startActivity(intent);
    }

    @Event(value = R.id.frag_2_yysmsg,type = View.OnClickListener.class)
    private void yysmsg(View v)
    {
        Intent intent=new Intent(getActivity(), StudyListActivity.class);
        intent.putExtra("ttype",700002);
        startActivity(intent);
    }
}
