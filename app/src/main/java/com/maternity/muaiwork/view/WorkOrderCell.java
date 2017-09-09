package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.common.CellCallBack;
import com.maternity.muaiwork.model.BaseModel;
import com.maternity.muaiwork.model.WorkOrderModel;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/7/17.
 */

public class WorkOrderCell extends BaseCell {
    class WorkOrderHodel{
        @ViewInject(R.id.cell_work_order_title)
        public TextView nameView;
        @ViewInject(R.id.cell_work_order_date)
        public TextView startTimeView;
        @ViewInject(R.id.cell_work_order_stateText)
        public TextView statView;
        @ViewInject(R.id.cell_work_order_statbutn)
        public Button statButton;

    }
    WorkOrderHodel hodel;
    public CellCallBack callBack;
    public WorkOrderCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_work_order,this);
        hodel=new WorkOrderHodel();
        x.view().inject(hodel,view);
//        view.setTag(hodel);
    }


    @Override
    public void setModelWithIndex(BaseModel modex, int index) {
        super.setModelWithIndex(modex, index);
        WorkOrderModel wmodel= (WorkOrderModel) modex;
        hodel.nameView.setText(wmodel.momName);
        hodel.startTimeView.setText(wmodel.startTime);
//        hodel.statView.setVisibility(GONE);
//        hodel.statButton.setVisibility(VISIBLE);
        /*工单状态（900001, 'WORKODERSTATUS', '派单中', '工单状态'),
                                                              	 (900002, 'WORKODERSTATUS', '设备领用中', '工单状态'),
                                                              	 (900003, 'WORKODERSTATUS', '上单中', '工单状态'),
                                                              	 (900004, 'WORKODERSTATUS', '工作中', '工单状态'),
                                                              	 (900005, 'WORKODERSTATUS', '设备归还中', '工单状态'),
                                                              	 (900006, 'WORKODERSTATUS', '工单完成', '工单状态'),
                                                              	 (900007, 'WORKODERSTATUS', '设备移交中', '工单状态'),
                                                              	 (900008, 'WORKODERSTATUS', '工单失败', '工单状态'））
                                                              	 */
        switch (wmodel.workOderStatus)
        {
            case 900001: {
                hodel.statView.setVisibility(VISIBLE);
                hodel.statButton.setVisibility(GONE);
                hodel.statView.setText("派单中");
                break;
            }
            case 900002:
            {
                hodel.statView.setVisibility(GONE);
                hodel.statButton.setVisibility(VISIBLE);
                hodel.statButton.setText("领用设备");
                break;
            }
            case 900003:
            {
                hodel.statView.setVisibility(GONE);
                hodel.statButton.setVisibility(VISIBLE);
                hodel.statButton.setText("立即上单");
                break;
            }
            case 900004:
            {
                hodel.statView.setVisibility(VISIBLE);
                hodel.statButton.setVisibility(GONE);
                hodel.statView.setText("工作中");
                break;
            }
            case 900005:
            {
                hodel.statView.setVisibility(GONE);
                hodel.statButton.setVisibility(VISIBLE);
                hodel.statButton.setText("设备归还");
                break;
            }
            case 900006:
            {
                hodel.statView.setVisibility(VISIBLE);
                hodel.statButton.setVisibility(GONE);
                hodel.statView.setText("工单完成");
                break;
            }
            case 900007:
            {
                {
                    hodel.statView.setVisibility(GONE);
                    hodel.statButton.setVisibility(VISIBLE);
                    hodel.statButton.setText("设备移交");
                    break;
                }
            }
            case 900008:
            {
                hodel.statView.setVisibility(VISIBLE);
                hodel.statButton.setVisibility(GONE);
                hodel.statView.setText("工单终止");
                break;
            }
            default:
                hodel.statView.setVisibility(GONE);
                hodel.statButton.setVisibility(GONE);
                break;
        }
        hodel.statButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton();
            }
        });

    }
//    @Event(type = OnClickListener.class,value = R.id.cell_work_order_statbutn)
    private void clickButton()
    {
        if (callBack!=null)
        {
            callBack.cellCallBackFromIndex(this.index,baseModel);
        }
    }
}
