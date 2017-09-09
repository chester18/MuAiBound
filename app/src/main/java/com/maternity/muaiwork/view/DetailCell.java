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
 * Created by apple on 16/10/12.
 */
//@ContentView(R.layout.cell_detail)
public class DetailCell extends BaseCell {
    class ViewHolde {
        @ViewInject(R.id.cell_img_lay)
        public LinearLayout img_lay;
        @ViewInject(R.id.cell_text_lay)
        public LinearLayout text_lay;
        @ViewInject(R.id.cell_main_lay)
        public LinearLayout main_lay;
        @ViewInject(R.id.cell_detail_lay)
        public LinearLayout detail_lay;
        @ViewInject(R.id.cell_arraw_lay)
        public LinearLayout arraw_lay;

        @ViewInject(R.id.cell_img)
        public ImageView cell_img;
        @ViewInject(R.id.cell_main)
        public TextView cell_main;
        @ViewInject(R.id.cell_text)
        public TextView cell_text;
        @ViewInject(R.id.cell_detail)
        public TextView cell_detail;
        @ViewInject(R.id.cell_arraw)
        public IconTextView cell_arraw;


    }
    ViewHolde holde;
    public DetailCell(Context context) {
        super(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cell_detail,this);
        holde=new ViewHolde();
        x.view().inject(holde,view);
        view.setTag(holde);

    }

    @Override
    public void init() {
        super.init();
        if (holde.cell_img!=null)
        {

        }
        if (holde.cell_main!=null)
        {
            holde.cell_main.setText("");
        }
        if (holde.cell_text!=null)
        {
            holde.cell_text.setText("");
        }
        if (holde.cell_detail!=null)
        {
            holde.cell_detail.setText("");
        }
    }

    @Override
    public void setModel(BaseModel model) {
        super.setModel(model);
        if (((DetailCellModel)((DetailCellModel)baseModel)).cimage==null)
        {
            holde.img_lay.setVisibility(GONE);
        }else {
            holde.img_lay.setVisibility(VISIBLE);
            holde.cell_img.setImageDrawable(((DetailCellModel)baseModel).cimage);
        }
        if (((DetailCellModel)baseModel).cmain==null)
        {
            holde.main_lay.setVisibility(GONE);
        }else {
            holde.main_lay.setVisibility(VISIBLE);
            holde.cell_main.setText(((DetailCellModel)baseModel).cmain);
        }
        if (((DetailCellModel)baseModel).ctext==null)
        {
            holde.text_lay.setVisibility(GONE);
        }else {
            holde.text_lay.setVisibility(VISIBLE);
            holde.cell_text.setText(((DetailCellModel)baseModel).ctext);
        }
        if (((DetailCellModel)baseModel).cdetail==null)
        {
            holde.detail_lay.setVisibility(GONE);
        }else {
            holde.detail_lay.setVisibility(VISIBLE);
            holde.cell_detail.setText(((DetailCellModel)baseModel).cdetail);
        }
    }

    public ViewHolde getHolde()
    {
        return holde;
    }
    public ImageView getCell_img() {
        return holde.cell_img;
    }

    public TextView getCell_main() {
        return holde.cell_main;
    }

    public TextView getCell_text() {
        return holde.cell_text;
    }

    public TextView getCell_detail() {
        return holde.cell_detail;
    }

    public IconTextView getCell_arraw() {
        return holde.cell_arraw;
    }
}
