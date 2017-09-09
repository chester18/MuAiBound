package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.maternity.muaiwork.R;

/**
 * Created by apple on 2017/9/6.
 */

public class DialogInfoText extends BaseDialog {
    TextView dia_title,dia_msg;
    Button shureBtn;
    public DialogInfoText(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_info_shure);
        dia_title= (TextView) findViewById(R.id.dialog_info_title);
        dia_msg= (TextView) findViewById(R.id.dialog_info_message);
        shureBtn= (Button) findViewById(R.id.dialog_info_button);
        shureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.selectShure(tag,msg);
                dismiss();
            }
        });
        dia_msg.setText(msg);
        dia_title.setText(title);

    }
}
