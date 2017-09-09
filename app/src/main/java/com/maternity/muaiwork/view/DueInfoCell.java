package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.model.BaseModel;
import com.maternity.muaiwork.model.NeedInfoModel;

/**
 * Created by apple on 2017/2/10.
 */

public class DueInfoCell extends BaseCell {
    NeedInfoModel needmodel;
    TextView customStatus;//预产期
    TextView priceview;//价格
    TextView grade;//等级城市
    TextView customNeed;//要求
    TextView sendDate;
    public DueInfoCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_need_info,this);
        customStatus= (TextView) view.findViewById(R.id.cell_due_udate);
        priceview= (TextView) view.findViewById(R.id.cell_due_price);
        grade= (TextView) view.findViewById(R.id.cell_due_city);
        customNeed= (TextView) view.findViewById(R.id.cell_due_need);
        sendDate= (TextView) view.findViewById(R.id.cell_due_senddate);

    }

    @Override
    public void setModel(BaseModel model) {
        needmodel= (NeedInfoModel) model;
        customStatus.setText(needmodel.dueDate);
        priceview.setText("￥"+needmodel.price);
        grade.setText(needmodel.city+"|"+needmodel.days+"|"+needmodel.grade);
        customNeed.setText(needmodel.customNeed);
        sendDate.setText(needmodel.sendDate);

    }
}
