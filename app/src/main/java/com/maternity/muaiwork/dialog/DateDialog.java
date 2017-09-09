package com.maternity.muaiwork.dialog;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.maternity.muaiwork.R;

import java.util.Date;

/**
 * Created by apple on 2016/10/19.
 */

public class DateDialog extends BaseDialog {
    TextView titleView;
    Button shure,cancle;
    TextView msgView;
    DatePicker adatePicker;
    public String dateString;
    public DateDialog(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date);
        titleView= (TextView) findViewById(R.id.date_title);
        msgView= (TextView) findViewById(R.id.date_msg);
        shure= (Button) findViewById(R.id.date_shure);
        cancle= (Button) findViewById(R.id.date_cancle);
        adatePicker= (DatePicker) findViewById(R.id.datePicker);
        titleView.setText(title);
        msgView.setText(msg);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectCancle(tag);
                DateDialog.this.dismiss();
            }
        });
        shure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.selectShure(tag,dateString);
                DateDialog.this.dismiss();
            }
        });
        dateString="";
        //Calendar c = Calendar.getInstance();
        adatePicker.init(2016, 10, 20, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                dateString=datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth();

            }

        });
        

    }
}
