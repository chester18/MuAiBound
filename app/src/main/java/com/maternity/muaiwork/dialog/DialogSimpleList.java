package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maternity.muaiwork.R;

/**
 * Created by apple on 2016/10/27.
 */

public class DialogSimpleList extends BaseDialog {
    TextView titieView;
    ListView lv;
    String[] strings={};
    BaseAdapter ba=new BaseAdapter() {
        @Override
        public int getCount() {
            return strings.length;
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
            TextView tv=new TextView(getContext());
            tv.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,88));
            tv.setGravity(Gravity.CENTER);
            tv.setText(strings[i]);

            return tv;
        }
    };
    public DialogSimpleList(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_simple_list);
        titieView= (TextView) findViewById(R.id.slist_title);
        lv= (ListView) findViewById(R.id.simple_list_list);
        titieView.setText(title);
        lv.setAdapter(ba);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                callBack.selectIndex(tag,i,strings[i]);
                dismiss();
            }
        });

    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}
