package com.maternity.muaiwork.fragment;

import android.app.Fragment;

import com.maternity.muaiwork.model.AnswerQuestionModel;
import com.maternity.muaiwork.model.QuestionModel;

/**
 * Created by apple on 2016/11/9.
 */

public class PBFragment extends Fragment {
    public QuestionModel model;
    public AnswerQuestionModel answerQuestionModel;
    String questionResult;

    public PBFragment() {
        super();
    }

//    public static PBFragment initPBFragment(QuestionModel question) {
//        PBFragment pb=new PBFragment();
//        pb.model=question;
//        return pb;
//    }


    public void setModel(QuestionModel model) {
        this.model = model;
    }

    public AnswerQuestionModel getResult()
    {
        return answerQuestionModel;
    }
    public void setAnswerQuestionModel(AnswerQuestionModel aqm)
    {
        answerQuestionModel=aqm;
    }
}
