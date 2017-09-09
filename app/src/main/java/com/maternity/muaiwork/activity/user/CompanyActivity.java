package com.maternity.muaiwork.activity.user;

import android.os.Bundle;
import android.view.View;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by apple on 2016/12/6.
 */
@ContentView(R.layout.activity_comp_address)
public class CompanyActivity extends CBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = R.id.comp_back,type = View.OnClickListener.class)
    private void back(View v)
    {
        finish();
    }
}
