package com.maternity.muaiwork.activity.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by apple on 2017/2/28.
 */
@ContentView(R.layout.activity_web_view)
public class WebViewActivity extends CBaseActivity {
    @ViewInject(R.id.web_webview)
    WebView webView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Intent intent=getIntent();
        //Bundle bundle=intent.getExtras();
        url=intent.getStringExtra("url");
        if (url!=null)
        {
            webView.loadUrl(url);
        }
    }
    @Event(value = R.id.wb_detail_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
}
