package com.maternity.muaiwork.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.order.NeedDetailActivity;
import com.maternity.muaiwork.activity.order.OrderDetailActivity;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.model.NeedInfoModel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(R.layout.activity_mawelcom)
public class MAWelcomActivity extends CBaseActivity {
    @ViewInject(R.id.logicon)
    ImageView logo;
    @ViewInject(R.id.icopy)
    TextView icopy;
    @ViewInject(R.id.idata)
    TextView rightdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        //setContentView(R.layout.activity_mawelcom);
        x.view().inject(this);
        PubFunction.readInfo(this);
        icopy.setText("Copyright © 2017-2018 Muaibang Inc. All Rights Reserved");
        rightdata.setText("版权所有 违者必究");
//        new  Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                handler.sendMessage(new Message());
//
//            }
//        }.start();
        if (logo!=null)
        {
            Animation roateAnima=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            roateAnima.setDuration(1000);
            roateAnima.setRepeatCount(3);
            roateAnima.setFillAfter(false);
            roateAnima.setRepeatMode(Animation.ZORDER_NORMAL);
            roateAnima.setInterpolator(new LinearInterpolator());
            roateAnima.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    handler.sendMessage(new Message());
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            logo.startAnimation(roateAnima);
        }else {
            handler.sendMessage(new Message());
        }

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (PubFunction.userid==0) {
                Intent intent = new Intent(MAWelcomActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }else {
                Intent intent =new Intent(MAWelcomActivity.this, MuAiMainTabActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };
}
