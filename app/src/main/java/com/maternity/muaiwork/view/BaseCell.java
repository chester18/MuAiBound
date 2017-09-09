package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.common.CellCallBack;
import com.maternity.muaiwork.model.BaseModel;
import com.maternity.muaiwork.model.DetailCellModel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by apple on 16/10/12.
 */
@ContentView(R.layout.cell_detail)
public class BaseCell extends LinearLayout {
    BaseModel baseModel;
    View view;
    int index;
    CellCallBack callBack;
    public BaseCell(Context context) {
        super(context);
//        LayoutInflater inflater=LayoutInflater.from(context);
//        view=inflater.inflate(R.layout)

    }

    public void init(){
    }

    public void setModel(BaseModel model)
    {
        baseModel= model;
    }
    public void setModelWithIndex(BaseModel modex,int index)
    {
        baseModel= modex;
        this.index=index;
    }

    public void setCallBack(CellCallBack callBack) {
        this.callBack = callBack;
    }
}

