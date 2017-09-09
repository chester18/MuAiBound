package com.maternity.muaiwork.activity.other;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.view.DetailCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/2/28.
 */
@ContentView(R.layout.activity_nomal_list)
public class MyRedpackageActivity extends CBaseActivity {
    @ViewInject(R.id.list_title)
    TextView title;
    @ViewInject(R.id.nomal_list)
    ListView listView;
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return redListmode.size();
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
            DetailCell cell=new DetailCell(MyRedpackageActivity.this);
            cell.setModel(new DetailCellModel(null,null,"我的红包","2017-1-10"));
            return cell;
        }
    };
    List<String> redListmode=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        title.setText("我的红包");
        for(int i=0;i<20;i++)
        {
            redListmode.add("我的红包");
        }
        listView.setAdapter(baseAdapter);
    }
    @Event(value = R.id.backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
}
