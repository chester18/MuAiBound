package com.maternity.muaiwork.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.model.AnswerQuestionModel;
import com.maternity.muaiwork.model.QuestionModel;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2016/10/28.
 */
@ContentView(R.layout.fragment_page_txt_ask)
public class PageTxtAskFragment extends PBFragment {
    @ViewInject(R.id.atxt_topic)
    TextView question;
    @ViewInject(R.id.atxt_resual)
    EditText ask;

    public PageTxtAskFragment() {
        super();
    }

//    public PageTxtAskFragment(QuestionModel question) {
//        super(question);
//    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=x.view().inject(this, inflater, container);

        if (model!=null)
        {
            question.setText(model.serial+"、"+model.content);
        }
        if (answerQuestionModel!=null)
        {
            ask.setText(answerQuestionModel.result);
        }
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        return v;
    }

    @Override
    public AnswerQuestionModel getResult() {
        if (answerQuestionModel==null) {
            answerQuestionModel = new AnswerQuestionModel(new JsonObject());
            answerQuestionModel.QuestionId = model.pid;
        }
        answerQuestionModel.result=ask.getText().toString();
        if(answerQuestionModel.result.length()==0)
        {
            return null;
        }
        return answerQuestionModel;
    }
//    @Event(value = R.id.atxt_topic,type = View.OnClickListener.class)
//    public void hideKey(View v)
//    {
//        ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); // (WidgetSearchActivity是当前的Activity)
//
//
//    }
}
