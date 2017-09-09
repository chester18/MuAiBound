package com.maternity.muaiwork.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;

import org.xutils.view.annotation.ContentView;

/**
 * Created by apple on 2016/10/19.
 */

public class BaseDialog extends Dialog {
    String title="";
    String msg="";
    int tag;
    public DialogCallBackInterface callBack;
    public BaseDialog(Context context,String title,String msg,int tag) {
        super(context);
        if (title!=null) {
            this.title = title;
        }else {
            this.title="";
        }
        this.msg=msg==null?"":msg;
        this.tag=tag==0?0:tag;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
