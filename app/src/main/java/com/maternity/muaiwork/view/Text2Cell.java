package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.maternity.muaiwork.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/2/16.
 */

public class Text2Cell extends BaseCell {
    class TxHodel{
        @ViewInject(R.id.cell_2text_title)
        TextView tx1;
        @ViewInject(R.id.cell_2text_detail)
        TextView tx2;

    }
    TxHodel myHodel;
    public Text2Cell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_2text,this);
        myHodel=new TxHodel();
        x.view().inject(myHodel,view);
        view.setTag(myHodel);
    }

    public void setTxtValue(String ctitle,String cdetail)
    {
        myHodel.tx1.setText(ctitle);
        myHodel.tx2.setText(cdetail);
    }
}
