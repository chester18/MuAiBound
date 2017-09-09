package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.maternity.muaiwork.R;

/**
 * Created by apple on 2017/9/5.
 */

public class DialogSuccess extends BaseDialog {
    TextView msgview;
    public DialogSuccess(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_success);
        msgview= (TextView) findViewById(R.id.dialog_success);
        msgview.setText(msg);
    }

    @Override
    public void show() {
        super.show();
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(new Message());

            }
        };
        thread.start();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DialogSuccess.this.dismiss();
        }
    };
}
