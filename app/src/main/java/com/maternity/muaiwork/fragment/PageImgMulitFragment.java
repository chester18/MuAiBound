package com.maternity.muaiwork.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by apple on 2016/10/28.
 */
@ContentView(R.layout.fragment_page_img_mulit)
public class PageImgMulitFragment extends PBFragment {
    @ViewInject(R.id.aimg_mtopic)
    TextView question;
    @ViewInject(R.id.aimg_mlist)
    ListView selectList;
    @ViewInject(R.id.aimg_mimage)
    ImageView image;

    List<String> selectResults;
    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            if (model.selectList==null)
                return 0;
            else
            return model.selectList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            SelectModel smo=model.selectList.get(i);
            CheckBox cb=new CheckBox(getActivity());
            cb.setTag(smo.serial);
            cb.setText(smo.serial+ " 、"+smo.content);
            if (selectResults.contains(smo.serial))
            {
                cb.setChecked(true);
            }
            cb.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,144));
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b)
                    {
                        selectResults.add(compoundButton.getTag().toString());
                    }else {
                        selectResults.remove(compoundButton.getTag().toString());
                    }
                }
            });
            return cb;
        }
    };
//    public PageImgMulitFragment(QuestionModel question) {
//        super(question);
//    }

    public PageImgMulitFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=x.view().inject(this, inflater, container);
        if (answerQuestionModel!=null)
        {
            String re=answerQuestionModel.result;
            selectResults= Arrays.asList(re.split(","));

        }else {
            selectResults=new ArrayList<>();
        }
        if (model!=null)
        {
            question.setText(model.serial+"、"+model.content);
//            AbImageLoader imgloader=AbImageLoader.newInstance(this.getActivity());
//            imgloader.display(image, CommonNetString.IMAGEURL+model.file);
            x.image().bind(image,CommonNetString.IMAGEURL+model.file);
            selectList.setAdapter(adapter);
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
        String srs="";
        for (String str:selectResults)
        {
            if (srs.length()>0)
            {
                srs=srs+","+str;
            }else {
                srs=str;
            }

        }
        answerQuestionModel.result=srs;
        if(answerQuestionModel.result.length()==0)
        {
            return null;
        }
        return answerQuestionModel;
    }
}
