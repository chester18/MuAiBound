package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RadioButton;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.model.BaobaoModel;
import com.maternity.muaiwork.model.BaseModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/2/16.
 */

public class Checkbox3Cell extends BaseCell {
    class Check3Hodel{
        @ViewInject(R.id.cell_3check_1)
        RadioButton rb1;
        @ViewInject(R.id.cell_3check_2)
        RadioButton rb2;
        @ViewInject(R.id.cell_3check_3)
        RadioButton rb3;
    }
    Check3Hodel myHodel;
    public Checkbox3Cell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_3checkout,this);
        myHodel=new Check3Hodel();
        x.view().inject(myHodel,view);
        view.setTag(myHodel);
    }

    @Override
    public void setModel(BaseModel model) {
        super.setModel(model);
        BaobaoModel model1= (BaobaoModel) model;
        if (model1.babatype>0)
        {
            typeModel(model1.babatype);
        }
    }

    private void typeModel(int type)
    {
        myHodel.rb1.setChecked(false);
        myHodel.rb2.setChecked(false);
        myHodel.rb3.setChecked(false);
        if (type==1) myHodel.rb1.setChecked(true);
        if (type==2) myHodel.rb2.setChecked(true);
        if (type==3) myHodel.rb3.setChecked(true);
    }
}
