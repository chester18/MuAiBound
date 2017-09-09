package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.maternity.muaiwork.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/2/24.
 */

public class MoreDetailCell extends BaseCell {
    public class MoreViewHodel{
        @ViewInject(R.id.cell_more_title)
        public TextView mTitle;
        @ViewInject(R.id.cell_more_detail)
        public TextView mDetail;
    }
    MoreViewHodel mHodel;
    public MoreDetailCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_more_text,this);
        mHodel=new MoreViewHodel();
        x.view().inject(mHodel,view);
        view.setTag(mHodel);
    }
    public MoreViewHodel getViewHodel()
    {
        return mHodel;
    }
}
