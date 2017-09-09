package com.maternity.muaiwork.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.model.AnswerQuestionModel;
import com.maternity.muaiwork.model.QuestionModel;
import com.maternity.muaiwork.model.SelectModel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2016/10/28.
 */
@ContentView(R.layout.fragment_page_img_single)
public class PageImgSingleFragment extends PBFragment {
    @ViewInject(R.id.aimg_stopic)
    TextView question;
    @ViewInject(R.id.aimg_slist)
    RadioGroup selectList;
    @ViewInject(R.id.aimg_simage)
    ImageView image;

    String selectResult;
//    public PageImgSingleFragment(QuestionModel question) {
//        super(question);
//    }


    public PageImgSingleFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=x.view().inject(this, inflater, container);
        if (model!=null)
        {
            question.setText(model.serial+"、"+model.content);
//            AbImageLoader imgloader=AbImageLoader.newInstance(this.getActivity());
//            imgloader.display(image, CommonNetString.IMAGEURL+model.file);
            x.image().bind(image,CommonNetString.IMAGEURL+model.file);
            question.setText(model.serial+"、"+model.content);
            if (model.selectList!=null)
            {
                for (SelectModel sm:model.selectList)
                {
                    RadioButton rb=new RadioButton(getActivity());
                    rb.setTag(sm.serial);
                    rb.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,144));
                    rb.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,144));
                    rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b)
                            {
                                selectResult= (String) compoundButton.getTag();
                            }
                        }
                    });
                    if (sm.serial.equals(selectResult))
                    {
                        rb.setChecked(true);
                    }
                    rb.setText(sm.serial+"、 "+sm.content);
                    selectList.addView(rb);
                }
            }
        }
        return v;
    }

    @Override
    public AnswerQuestionModel getResult() {
        if (answerQuestionModel==null)
        {
            answerQuestionModel = new AnswerQuestionModel(new JsonObject());
            answerQuestionModel.QuestionId = model.pid;
        }
        answerQuestionModel.result=selectResult;
        if(answerQuestionModel.result.length()==0)
        {
            return null;
        }
        return answerQuestionModel;
    }
}
