package com.maternity.muaiwork.activity.work;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.dialog.DialogCallBackInterface;
import com.maternity.muaiwork.dialog.DialogDateTime;
import com.maternity.muaiwork.model.ApplyLeaveModel;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/6/26.
 * 请假申请
 */
@ContentView(R.layout.activity_apply_leave)
public class ApplyLeaveActivity extends CBaseActivity {
    @ViewInject(R.id.apply_reason)
    EditText reson;
    @ViewInject(R.id.apply_start)
    Button startBtn;
    @ViewInject(R.id.apply_end)
            Button endBtn;
    String start,end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

    }

    @Event(value = R.id.apply_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        setResult(10);
        finish();
    }
    @Event(value = R.id.apply_start,type = View.OnClickListener.class)
    private void startTime(View v)
    {
        DialogDateTime dateTime=new DialogDateTime(this,"起始时间","",10);
        dateTime.callBack=new DialogCallBackInterface() {
            @Override
            public void selectShure(int tag, Object result) {
                start= (String) result;
                startBtn.setText("起始时间:"+start);
            }

            @Override
            public void selectCancle(int tag) {

            }

            @Override
            public void selectIndex(int tag, int index, Object object) {

            }
        };
        dateTime.show();

    }
    @Event(value = R.id.apply_end,type = View.OnClickListener.class)
    private void endTime(View v)
    {
        DialogDateTime dateTime=new DialogDateTime(this,"结束时间","",20);
        dateTime.callBack=new DialogCallBackInterface() {
            @Override
            public void selectShure(int tag, Object result) {
                end= (String) result;
                endBtn.setText("结束时间:"+end);
            }

            @Override
            public void selectCancle(int tag) {

            }

            @Override
            public void selectIndex(int tag, int index, Object object) {

            }
        };
        dateTime.show();
//        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.apply_submit,type = View.OnClickListener.class)
    private void submit(View v)
    {
        if (start==null || end==null)
        {
            showToast("起始时间、结束时间必须选择！");
            return;
        }
        ApplyLeaveModel applyModel=new ApplyLeaveModel();
        applyModel.startTime=start;
        applyModel.uid= PubFunction.userid;
        applyModel.endTime=end;
        applyModel.reason=reson.getText().toString();
        sendPost(CommonNetString.leaveRequest,applyModel.getJsonObject(),"提交申请...",200);
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        JsonObject root=new JsonParser().parse(result).getAsJsonObject();
        if (root.get("result").getAsInt()>0) {
            setResult(10);
            showToast("提交完成，等待审核！");
            finish();
            return;
        }else {
            showToast(root.get("msg").getAsString());
        }
    }


}
