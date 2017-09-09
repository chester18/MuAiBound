package com.maternity.muaiwork.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maternity.muaiwork.R;

/**
 * Created by apple on 2016/10/20.
 */

public class SelectImageSourceDialog extends BaseDialog {
    TextView titleView;
    Button img,cam;

    public SelectImageSourceDialog(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_image);
        titleView= (TextView) findViewById(R.id.image_title);
        img= (Button) findViewById(R.id.image_lib);
        cam= (Button) findViewById(R.id.image_cam);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectCancle(tag);
                dismiss();
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectShure(tag,"");
                dismiss();
            }
        });
        titleView.setText(title);

    }
}
