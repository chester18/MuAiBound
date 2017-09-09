package com.maternity.muaiwork.activity.other;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/2/28.
 */
@ContentView(R.layout.activity_myschedule)
public class MyScheduleActivity extends CBaseActivity {
    @ViewInject(R.id.datePicker2)
    DatePicker datePicker;
    @ViewInject(R.id.sch_start)
    Button startBtn;
    @ViewInject(R.id.sch_end)
    Button endBtn;
    boolean isStart;
    String startString,endString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        isStart=true;
        datePicker.init(2017, 2, 12, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                if (isStart)
                {
                    startString=i+"-"+i1+"-"+i2;
                    startBtn.setText(startString);

                }else {
                    endString=i+"-"+i1+"-"+i2;
                    endBtn.setText(endString);
                }
            }
        });

    }

    @Event(value = R.id.sch_backICO,type = View.OnClickListener.class)
    private void backClick(View v) {
        finish();
    }

    @Event(value = R.id.sch_start,type = View.OnClickListener.class)
    private void selectStart(View v) {
        isStart=true;
        showToast("请选择起始日期！");
    }
    @Event(value = R.id.sch_end,type = View.OnClickListener.class)
    private void selectEnd(View v) {
        isStart=false;
        showToast("请选择结束日期！");
    }
    @Event(value = R.id.sch_submit,type = View.OnClickListener.class)
    private void submit(View v) {
        //提交档期

    }

}
