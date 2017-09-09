package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.model.ApplyLeaveModel;
import com.maternity.muaiwork.model.BaseModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/7/7.
 */

public class LeaveHistoryCell extends BaseCell {
    class ViewHolde {
        @ViewInject(R.id.cell_history_status)
        public ImageView status;
        @ViewInject(R.id.cell_history_start)
        public TextView start;
        @ViewInject(R.id.cell_history_end)
        public TextView end;
        @ViewInject(R.id.cell_history_reason)
        public TextView reason;
        @ViewInject(R.id.cell_history_status_info)
        public TextView statusText;
    }
    ViewHolde viewHolde=new ViewHolde();
    public LeaveHistoryCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_leave_history,this);
        x.view().inject(viewHolde,view);
        view.setTag(viewHolde);
    }

    @Override
    public void setModel(BaseModel model) {
        super.setModel(model);
        ApplyLeaveModel alm= (ApplyLeaveModel) model;
        viewHolde.start.setText("开始时间："+alm.startTime);
        viewHolde.end.setText("结束时间："+alm.endTime);
        viewHolde.reason.setText(alm.reason);
        String st="";
        if (alm.mstatus==0){
            st="待审核";
        }else if (alm.mstatus==1){
            st="不通过";

        }else if (alm.mstatus==2)
            st="通过";

        viewHolde.statusText.setText(st);
    }
}
