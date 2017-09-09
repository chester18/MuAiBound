package com.maternity.muaiwork.dialog;

import android.content.Context;
import java.util.Calendar;
//import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import com.maternity.muaiwork.R;

import java.util.Date;

/**
 * Created by apple on 2017/6/26.
 */

public class DialogDateTime extends BaseDialog {
    TextView titleText,selectDateTime;
    DatePicker datePicker;
    TimePicker timePicker;
    Button shure,cancle;
    public int mYear,mMonth,mDay,mHour,mMinute;
    public DialogDateTime(Context context, String title, String msg, int tag) {
        super(context, title, msg, tag);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_time);
        titleText= (TextView) findViewById(R.id.date_time_title);
        selectDateTime= (TextView) findViewById(R.id.dialog_select_date_time);
        datePicker= (DatePicker) findViewById(R.id.dialog_datePicker);
        timePicker= (TimePicker) findViewById(R.id.dialog_timePicker);
        shure= (Button) findViewById(R.id.date_time_shure);
        cancle= (Button) findViewById(R.id.date_time_cancle);
        titleText.setText(title);
        Calendar c = Calendar.getInstance();//
        mYear = c.get(Calendar.YEAR); // 获取当前年份
        mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
//        int mWay = c.get(Calendar.DAY_OF_WEEK);// 获取当前日期的星期
        mHour = c.get(Calendar.HOUR_OF_DAY);//时
        mMinute = c.get(Calendar.MINUTE);//分
        selectDateTime.setText(mYear+"年"+mMonth+"月"+mDay+"日 "+mHour+":"+mMinute);
        datePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear=year;
                mMonth=monthOfYear+1;
                mDay=dayOfMonth;
                selectDateTime.setText(mYear+"年"+mMonth+"月"+mDay+"日 "+mHour+":"+mMinute);
            }
        });
        timePicker.setIs24HourView(true);
//        timePicker.setHour(mHour);
//        timePicker.setMinute(mMinute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour=hourOfDay;
                mMinute=minute;

                selectDateTime.setText(mYear+"年"+mMonth+"月"+mDay+"日 "+mHour+":"+mMinute);
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.selectCancle(tag);
                DialogDateTime.this.dismiss();
            }
        });
        shure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.selectShure(tag,mYear+"-"+mMonth+"-"+mDay+" "+mHour+":"+mMinute+":00");
                DialogDateTime.this.dismiss();
            }
        });

    }
}
