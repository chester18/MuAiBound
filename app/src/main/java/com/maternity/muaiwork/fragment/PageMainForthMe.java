package com.maternity.muaiwork.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.user.DetailUserInfoActivity;
import com.maternity.muaiwork.activity.work.ApplyLeaveActivity;
import com.maternity.muaiwork.activity.work.MyWorkOrderActivity;
import com.maternity.muaiwork.activity.work.RequestLeaveActivity;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.view.DetailCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/6/19.
 */
@ContentView(R.layout.fragment_main_forth_me)
public class PageMainForthMe extends BaseFragment {
    @ViewInject(R.id.frag_four_me)
    private ListView listView;
    String[] icos=new String[]{"{icon-notebook}","{icon-docs}","{icon-calendar}"};
    String[] names=new String[]{"个人信息","我的工单","我要请假"};

    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return 3;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            DetailCellModel model=new DetailCellModel(null,null,names[position],"");
            DetailCell cell=new DetailCell(getActivity());
            cell.setModel(model);
            cell.setLayoutParams(new AbsListView.LayoutParams((int) (fragWidth*1.5), -2));
            cell.getCell_text().setTextColor(Color.GRAY);
            cell.setBackgroundColor(Color.WHITE);
            return cell;
        }
    };
    public PageMainForthMe()
    {

        super();
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WindowManager wm = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
        //getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        fragWidth = metrics.widthPixels;   //getDefaultDisplay().getWidth();
        fragHight = metrics.heightPixels;
        View v= x.view().inject(this,inflater,container);
        if (listView!=null)
        {
            listView.setAdapter(adapter);
        }
        return v;
    }

    @Event(value = R.id.frag_four_me, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        switch (position)
        {
            case 0:
                //个人信息
                Intent intent = new Intent(getActivity(), DetailUserInfoActivity.class);
                intent.putExtra("isEdit",false);
                startActivity(intent);
                break;
            case 1:
                //我的工单
                Intent intentw=new Intent(getActivity(), MyWorkOrderActivity.class);
                startActivity(intentw);
                break;
            case 2:
                //我要请假
                Intent appintent=new Intent(getActivity(), RequestLeaveActivity.class);
                startActivity(appintent);
                break;
            default:
                break;
        }
    }
}
