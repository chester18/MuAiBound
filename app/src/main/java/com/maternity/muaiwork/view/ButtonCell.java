package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import com.maternity.muaiwork.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2016/10/19.
 */

public class ButtonCell extends BaseCell {
    public CellCallBackInterface callBack=null;
    public class BtnViewHolder{
        @ViewInject(R.id.cell_button)
        TextView btn;
    }
    BtnViewHolder holder;
    public ButtonCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_btn,this);
        holder=new BtnViewHolder();
        x.view().inject(holder,view);
        view.setTag(holder);
    }

    public interface CellCallBackInterface{
        public void cellCallBack(int index,Object data);
    }
}
