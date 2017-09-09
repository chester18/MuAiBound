package com.maternity.muaiwork.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.dialog.DialogCallBackInterface;
import com.maternity.muaiwork.dialog.DialogFactry;
import com.maternity.muaiwork.dialog.DialogSuccess;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by apple on 2017/9/6.
 */
@ContentView(R.layout.activity_userlogin)
public class PageMainLogin extends BaseFragment {
    @ViewInject(R.id.uname)
    EditText useName;
    @ViewInject(R.id.upass)
    EditText code;
    @ViewInject(R.id.login_send_code)
    Button sendBtn;
    EventHandler eventHandler;
    Timer stimer;
    int count=120;
    public PageMainLogin() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v=x.view().inject(this,inflater,container);

        SMSSDK.setAskPermisionOnReadContact(true);
        eventHandler=new EventHandler(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterEvent(int i, int i1, Object o) {
                Message msg=new Message();
                if (i1 == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (i == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
msg.what=30;
                        handler.sendMessage(msg);

                    }else if (i == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        msg.what=20;
                        handler.sendMessage(msg);

                    }else if (i ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    msg.what=40;
                    handler.sendMessage(msg);
                    ((Throwable)o).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler);

        return v;

    }

    //消息
    @Event(value = R.id.login_msg,type = View.OnClickListener.class)
    private void receiveMsg(View view)
    {

    }
    @Event(value = R.id.login_send_code,type = View.OnClickListener.class)
            private void sendCode(View view)
    {
        if (useName.getText().toString().length()!=11)
        {
            showToast("请正确填写手机号！");
            return;
        }
        //发送验证码
        SMSSDK.getVerificationCode("86",useName.getText().toString());

        countTime();

    }

    @Event(value = R.id.ulogin,type = View.OnClickListener.class)
    private void submit(View view)
    {
        SMSSDK.submitVerificationCode("86",useName.getText().toString(),code.getText().toString());
    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        JsonObject object= new JsonParser().parse(result).getAsJsonObject();
//        showToast(object.get("msg").toString());
        JsonObject user=object.getAsJsonObject("data");
        PubFunction.userid=user.get("id").getAsInt();
        PubFunction.userName=user.get("userName").getAsString();
        PubFunction.useType=user.get("typeId").getAsInt();
        PubFunction.saveInfo(getActivity());
        activityCallback.sendCommand("page1");
    }

    @Override
    public void onDestroy() {
        SMSSDK.unregisterEventHandler(eventHandler);
        super.onDestroy();
    }
    //120秒倒计时
    private void countTime()
    {
        count=120;
        stimer=new Timer();
        sendBtn.setText(count+"");
        stimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=10;
                handler.sendMessage(new Message());
            }
        },1000,1000);
    }

    Handler handler=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10: {
                    count--;
                    if (count > 0) {
                        sendBtn.setText(count + "");
                    } else {
                        sendBtn.setText("重发");
                        stimer.cancel();
                    }
                    break;
                }
                case 20:{
                    DialogSuccess dialogSuccess= (DialogSuccess) DialogFactry.instance(getActivity(), DialogFactry.DIA_SUCC, 100, "", "发送成功", new DialogCallBackInterface() {
                        @Override
                        public void selectShure(int tag, Object result) {

                        }

                        @Override
                        public void selectCancle(int tag) {

                        }

                        @Override
                        public void selectIndex(int tag, int index, Object object) {

                        }
                    });
                    dialogSuccess.show();
                    break;
                }
                case 30:
                {
                    JsonObject ojs=new JsonObject();
                    ojs.addProperty("name",useName.getText().toString());

                    sendPost(CommonNetString.maLogin,ojs,"登录中...",1);
                    break;
                }
                case 40:
                    //发送失败
                    break;
                default:
                    break;

            }

        }
    };
}
