package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.model.BaseModel;
import com.maternity.muaiwork.model.WorkHistoryModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/8/21.
 */

public class WorkRecorderCell extends BaseCell {
    class ViewHolde {
        @ViewInject(R.id.work_order_title_name)
        TextView nameView;
        @ViewInject(R.id.work_order_select_value)
        TextView valueView;
        @ViewInject(R.id.work_order_take_photo)
        ImageButton takePhoto;
        @ViewInject(R.id.work_order_photo_count)
        TextView photoCount;

    };
    ViewHolde viewHolde;
    public WorkRecorderCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_work_recorder_show,this);
        viewHolde=new ViewHolde();
        x.view().inject(viewHolde,view);
        view.setTag(viewHolde);
    }



    @Override
    public void setModelWithIndex(final BaseModel modex, final int index) {
        super.setModelWithIndex(modex, index);
        WorkHistoryModel wmodel= (WorkHistoryModel) modex;
        viewHolde.nameView.setText(wmodel.workRecordItemName);
        viewHolde.valueView.setText(wmodel.workRecordListValue.equals("")?"暂无":wmodel.workRecordListValue);
        int countImg=0;
        if (!"".equals(wmodel.workRecordListImages))
        {
            String[] trs=wmodel.workRecordListImages.split(",");
            countImg=trs.length;
        }
        viewHolde.photoCount.setText(countImg+"张");
        viewHolde.takePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.cellCallBackFromIndex(index,modex);
            }
        });
    }
}
