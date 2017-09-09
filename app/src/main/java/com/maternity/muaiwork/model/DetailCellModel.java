package com.maternity.muaiwork.model;

import android.graphics.drawable.Drawable;

import com.maternity.muaiwork.view.DetailCell;

/**
 * Created by apple on 16/10/12.
 */

public class DetailCellModel extends BaseModel {
    public Drawable cimage;
    public String cmain;
    public String ctext;
    public String cdetail;
    public String icotext;
    public String tailtext;

    public DetailCellModel() {
        super();

    }
    public DetailCellModel(Drawable cimage,String cmain,String ctext,String cdetail)
    {
        if(cimage!=null) {
            this.cimage = cimage;
        }
        if (cmain!=null)
        this.cmain=cmain;
        if (ctext!=null)
        this.ctext=ctext;
        if (cdetail!=null)
        this.cdetail=cdetail;
    }
    public DetailCellModel(String icotext,String cmain,String ctext,String cdetail,String tailtext)
    {
        this.icotext=icotext;
        this.cmain=cmain;
        this.ctext=ctext;
        this.cdetail=cdetail;
        this.tailtext=tailtext;

    }
}
