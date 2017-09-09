package com.maternity.muaiwork.activity.paper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.joanzapata.iconify.widget.IconTextView;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.dialog.BaseDialog;
import com.maternity.muaiwork.dialog.DialogCallBackInterface;
import com.maternity.muaiwork.dialog.DialogFactry;
import com.maternity.muaiwork.dialog.DialogText;
import com.maternity.muaiwork.fragment.PBFragment;
import com.maternity.muaiwork.fragment.PageImgAskFragment;
import com.maternity.muaiwork.fragment.PageImgMulitFragment;
import com.maternity.muaiwork.fragment.PageImgSingleFragment;
import com.maternity.muaiwork.fragment.PageTxtAskFragment;
import com.maternity.muaiwork.fragment.PageTxtMulitFragment;
import com.maternity.muaiwork.fragment.PageTxtSingleFragment;
import com.maternity.muaiwork.model.AnswerPaperModel;
import com.maternity.muaiwork.model.AnswerQuestionModel;
import com.maternity.muaiwork.model.PaperModel;
import com.maternity.muaiwork.model.QuestionModel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

/**
 * Created by apple on 2016/10/28.
 */
@ContentView(R.layout.activity_paper)
public class PaperActivity extends CBaseActivity {
    List<AnswerQuestionModel> answers=new ArrayList<AnswerQuestionModel>();
    PaperModel paper;
    int answerPaperId=-1;                          //答题试卷号
    int paperid=0;                              //试题号
    int currentQuestion;                        //当前题号
    int totalTime=30;                              //剩余时间
    @ViewInject(R.id.paper_fragment)
    LinearLayout fragmentContent;
    @ViewInject(R.id.paper_backICO)
    IconTextView backBtn;
    @ViewInject(R.id.paper_timer)
    TextView timeText;
    @ViewInject(R.id.paper_title)
    TextView titleView;
    @ViewInject(R.id.paper_up)
    Button up;
    @ViewInject(R.id.paper_next)
    Button down;
    PBFragment currFragment;
    Timer timer;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            totalTime--;
            if (totalTime<=0)
            {
                timer.cancel();
                BaseDialog bd=DialogFactry.instance(PaperActivity.this, DialogFactry.DIA_TEXT, 2, "计时结束", "答题时间已经结束!请点击\"确定\"按钮提交答题结果!", new DialogCallBackInterface() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void selectShure(int tag, Object result) {
                        //提交试卷
                        submitAnswer();


                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                ((DialogText)bd).cancle.setVisibility(View.GONE);
                bd.show();
            }else {
                timeText.setText(totalTime+"m");
            }

        }
    };
///提交答案
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void submitAnswer() {
        JsonObject so=new JsonObject();
        so.addProperty("paperid",paperid);
        so.addProperty("userid", PubFunction.userid);
        if (answerPaperId!=-1)
            so.addProperty("answerId",answerPaperId);
        JsonArray aj=new JsonArray();
        for (int i=0;i<answers.size();i++)
        {
            aj.add(answers.get(i).getJsonObject());
            
        }
        so.add("answers",aj);
//        so.addProperty("answers",aj);
        sendPost(CommonNetString.paperAsk,so,"提交答卷...",200);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        currentQuestion=-1;
        answers=new ArrayList<AnswerQuestionModel>();
        Bundle bundle=getIntent().getBundleExtra("data");
        paperid=bundle.getInt("paperId");
        getPaper(paperid);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getPaper(int paperid)
    {
        JsonObject njo=new JsonObject();
        njo.addProperty("paperId",1);
        sendPost(CommonNetString.paperInfo,njo,"获取试卷...",100);
//        String Result="";
//        try {
//            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open("p.html") );
//            BufferedReader bufReader = new BufferedReader(inputReader);
//            String line="";
//            while((line = bufReader.readLine()) != null)
//                Result += line;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JsonObject pj=new  JsonParser().parse(Result).getAsJsonObject();
//        paper=new PaperModel(pj);
//        selectQuestion(0);
    }


    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100) {
            JsonObject sj = new JsonParser().parse(result).getAsJsonObject();
            paper = new PaperModel(sj.getAsJsonObject("data"));
            selectQuestion(currentQuestion + 1);
            //开始计时
            startTimer();
        }
        if(tag==200)
        {
            JsonObject jo=new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsBoolean())
            {
                showToast("答卷完成！");
                finish();
            }
        }

    }

    private void startTimer() {
        //倒计时
        timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                handler.sendMessage(new Message());
            }
        };
        timer.schedule(task,1000*60);


    }


    private void selectQuestion(int index)
    {
        QuestionModel question=paper.questionList.get(index);
        PBFragment fragment=null;
        switch (question.questionTypeId)
        {
            case 300001:
                fragment=new PageTxtAskFragment();
                break;
            case 300002:
                fragment=new PageTxtSingleFragment();
                break;
            case 300003:
                fragment=new PageTxtMulitFragment();
                break;
            case 300004:
                fragment=new PageImgAskFragment();
                break;
            case 300005:
                fragment=new PageImgSingleFragment();
                break;
            case 300006:
                fragment=new PageImgMulitFragment();
                break;
            default:
                break;
        }
        fragment.setModel(question);
        if (fragment!=null) {


            for (AnswerQuestionModel aqm:answers)
            {
                if (aqm.QuestionId==question.pid)
                {
                    fragment.answerQuestionModel=aqm;
                    break;
                }
            }
            if (index > currentQuestion) {
                //next
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.setCustomAnimations(R.animator.page_next_in,R.animator.page_next_out);
                ft.replace(R.id.paper_fragment,fragment);
                ft.commit();
            } else {
                //up
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.setCustomAnimations(R.animator.page_up_in,R.animator.page_up_out);
                ft.replace(R.id.paper_fragment,fragment);
                ft.commit();
            }
            currFragment=fragment;
            currentQuestion = index;
            if (currentQuestion==paper.questionList.size()-1)
            {
                down.setText("完成并提交");
            }
            titleView.setText("月嫂初试("+(currentQuestion+1)+"/"+paper.questionList.size()+")");
        }
    }
    //获取答案
    private void getPaperAsk(PBFragment bfragment)
    {
        AnswerQuestionModel mode=bfragment.getResult();
        if (mode!=null)
        {
            for (int i=0;i<answers.size();i++)
            {
                AnswerQuestionModel am=answers.get(i);
                if (am.QuestionId==mode.QuestionId)
                {
                    //Collections.replaceAll(answers,am,mode);
                    answers.remove(i);
                    break;

                }
            }

            answers.add(mode);

        }
    }

    //按钮点击响应
    @Event(value = R.id.paper_backICO,type = View.OnClickListener.class)
    private void backClick(View v)              //返回按钮
    {
        String msg="您确定放弃答题吗？";
        BaseDialog dia= DialogFactry.instance(this,DialogFactry.DIA_TEXT,1,"结束答题",msg,new DialogCallBackInterface(){

            @Override
            public void selectShure(int tag, Object result) {
                timer.cancel();
                PaperActivity.this.finish();
            }

            @Override
            public void selectCancle(int tag) {


            }

            @Override
            public void selectIndex(int tag, int index, Object object) {

            }
        });
        dia.show();
    }

    @Event(value = R.id.paper_next,type = View.OnClickListener.class)
    private void nextP(View v)
    {
        getPaperAsk(currFragment);
        if (currentQuestion==paper.questionList.size()-1)
        {
            String msg="您确定提交答题吗？";
            BaseDialog dia= DialogFactry.instance(this,DialogFactry.DIA_TEXT,1,"结束答题",msg,new DialogCallBackInterface(){

                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void selectShure(int tag, Object result) {
                    submitAnswer();
                }

                @Override
                public void selectCancle(int tag) {

                }

                @Override
                public void selectIndex(int tag, int index, Object object) {

                }
            });
            dia.show();
            return;
        }
        //下一题
        if (currentQuestion<paper.questionList.size()-1)
        {
            selectQuestion(currentQuestion+1);
        }
    }
    @Event(value = R.id.paper_up,type = View.OnClickListener.class)
    private void upP(View v)
    {
        //上一题
        getPaperAsk(currFragment);
        if (currentQuestion>0)
        {
            selectQuestion(currentQuestion-1);
            //down.setText("下一题");
        }
    }


}


