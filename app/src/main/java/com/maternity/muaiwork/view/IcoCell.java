package com.maternity.muaiwork.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.model.BaseModel;
import com.maternity.muaiwork.model.DetailCellModel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 16/10/13.
 */
//@ContentView(R.layout.cell_ico_text)
public class IcoCell extends BaseCell {
    public class ICViewHolder

    {
        @ViewInject(R.id.ico_img_lay)
        public LinearLayout img_lay;
        @ViewInject(R.id.ico_text_lay)
        public LinearLayout text_lay;
        @ViewInject(R.id.ico_main_lay)
        public LinearLayout main_lay;
        @ViewInject(R.id.ico_detail_lay)
        public LinearLayout detail_lay;
        @ViewInject(R.id.ico_arraw_lay)
        public LinearLayout arraw_lay;

        @ViewInject(R.id.ico_img)
        public IconTextView cell_img;
        @ViewInject(R.id.ico_main)
        public IconTextView cell_main;
        @ViewInject(R.id.ico_text)
        public IconTextView cell_text;
        @ViewInject(R.id.ico_detail)
        public IconTextView cell_detail;
        @ViewInject(R.id.ico_arraw)
        public IconTextView cell_arraw;
    }
    public ICViewHolder holder;
    public IcoCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_ico_text,this);
        holder=new ICViewHolder();
        x.view().inject(holder,view);
        view.setTag(holder);
        
        //init();

    }

    @Override
    public void init() {
        super.init();
        if (holder.cell_img!=null)
        {
            holder.cell_img.setText("");
        }
        if (holder.cell_main!=null)
        {
            holder.cell_main.setText("");
        }
        if (holder.cell_text!=null)
        {
            holder.cell_text.setText("");
        }
        if (holder.cell_detail!=null)
        {
            holder.cell_detail.setText("");
        }
    }

    @Override
    public void setModel(BaseModel model) {
        super.setModel(model);
        DetailCellModel baseModel= (DetailCellModel) this.baseModel;
        if (baseModel.icotext==null)
        {
            holder.img_lay.setVisibility(GONE);
        }else {
            holder.img_lay.setVisibility(VISIBLE);
            holder.cell_img.setText(baseModel.icotext);
        }
        if (baseModel.cmain==null)
        {
            holder.main_lay.setVisibility(GONE);
        }else {
            holder.main_lay.setVisibility(VISIBLE);
            holder.cell_main.setText(baseModel.cmain);
        }
        if (baseModel.ctext==null)
        {
            holder.text_lay.setVisibility(GONE);
        }else {
            holder.text_lay.setVisibility(VISIBLE);
            holder.cell_text.setText(baseModel.ctext);
        }
        if (baseModel.cdetail==null)
        {
            holder.detail_lay.setVisibility(GONE);
        }else {
            holder.detail_lay.setVisibility(VISIBLE);
            holder.cell_detail.setText(baseModel.cdetail);
        }
        if (baseModel.tailtext==null)
        {
            holder.arraw_lay.setVisibility(GONE);
        }else {
            holder.arraw_lay.setVisibility(VISIBLE);
            holder.cell_arraw.setText(baseModel.tailtext);
        }
    }
}
