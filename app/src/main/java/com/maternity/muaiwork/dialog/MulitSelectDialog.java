package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.maternity.muaiwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2016/11/8.
 */

public class MulitSelectDialog extends BaseDialog {
    TextView muTitle,muMsg;
    ListView muList;
    Button mushure,mucancle;
    List<String> results;
    String[] selectItems;
//    List<CheckBox> checkList;

    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return selectItems.length;
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            CheckBox checkBox=new CheckBox(getContext());
            checkBox.setText(selectItems[i]);
            checkBox.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,120));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String result=selectItems[i];
                    if (b)
                    {
                        results.add(result);
                    }else {
                        results.remove(result);
                    }
                }
            });
            checkBox.setTextSize(16);
            return checkBox;
        }
    };

    public MulitSelectDialog(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_mulit_list);
        muTitle= (TextView) findViewById(R.id.mulite_title);
        muList= (ListView) findViewById(R.id.mulite_listview);
        mushure= (Button) findViewById(R.id.mulite_shure);
        mucancle= (Button) findViewById(R.id.mulite_cancle);
//        checkList=new ArrayList<CheckBox>();
        results=new ArrayList<String>();
        muTitle.setText(title);
        muList.setAdapter(adapter);
        mushure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectShure(tag,getSelectString());
                dismiss();
            }
        });
        mucancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        muMsg= (TextView) findViewById(R.id.mulit_msg);
        muMsg.setText(msg);

    }

    public void setSelectItems(String[] selectItems) {
        this.selectItems = selectItems;
    }

    private String getSelectString()
    {
        StringBuilder stringBuilder=new StringBuilder("");
        for (int i=0;i<results.size();i++)
        {
            if (i==0)
            {
                stringBuilder.append(results.get(i));
            }else {
                stringBuilder.append(","+results.get(i));
            }
        }
        return stringBuilder.toString();
    }
}
