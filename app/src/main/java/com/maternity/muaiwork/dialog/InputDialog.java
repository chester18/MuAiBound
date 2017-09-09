package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maternity.muaiwork.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by apple on 2016/10/19.
 */

public class InputDialog extends BaseDialog {
    TextView qtitleView;
    Button shure,cancle;
    TextView msgView;
    public EditText input;
    public InputDialog(Context context, String title, String msg,int tag) {
        super(context, title, msg,tag);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input);
        qtitleView= (TextView) findViewById(R.id.input_title);
        qtitleView.setText(title);
        msgView= (TextView) findViewById(R.id.input_msg);
        msgView.setText(msg);
        input= (EditText) findViewById(R.id.input_edit);
        shure= (Button) findViewById(R.id.input_shure);
        shure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectShure(tag,input.getText().toString());
                InputDialog.this.dismiss();
            }
        });
        cancle= (Button) findViewById(R.id.input_cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectCancle(tag);
                InputDialog.this.dismiss();
            }
        });



    }
}
