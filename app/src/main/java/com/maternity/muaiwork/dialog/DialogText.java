package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.maternity.muaiwork.R;

/**
 * Created by apple on 2016/11/10.
 */

public class DialogText extends BaseDialog {
    TextView titleView;
    public Button shure,cancle;
    TextView msgView;

    public DialogText(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_text);
        titleView= (TextView) findViewById(R.id.txt_title);
        shure= (Button) findViewById(R.id.txt_shure);
        cancle= (Button) findViewById(R.id.txt_cancle);
        msgView= (TextView) findViewById(R.id.txt_textView);
        titleView.setText(title);
        msgView.setText(msg);
        shure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectShure(tag,msg);
                dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectCancle(tag);
                dismiss();
            }
        });

    }
}
