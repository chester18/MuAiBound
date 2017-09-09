package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.maternity.muaiwork.R;

/**
 * Created by apple on 2017/2/8.
 */

public class DialogLoading extends BaseDialog {
    ImageView loadImage;
    TextView msgText;
    int iconId=0;
    Animation animation;
    public DialogLoading(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        loadImage= (ImageView) findViewById(R.id.loading_image);
        msgText= (TextView) findViewById(R.id.loading_text);


    }

    public DialogLoading setIconId(int iconId) {
        this.iconId = iconId;
        return this;
    }

    public DialogLoading setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public void dismiss() {
        animation.cancel();
        super.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void show() {
        super.show();
        if (msg.length()>1)
            msgText.setText(msg);
        if (iconId>0)
        {
            loadImage.setImageDrawable(getContext().getResources().getDrawable(iconId,null));
        }
        animation=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(4000);
        animation.setRepeatCount(Animation.INFINITE);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        if (loadImage!=null)
        {
            loadImage.setAnimation(animation);
        }

        animation.start();
    }
}
