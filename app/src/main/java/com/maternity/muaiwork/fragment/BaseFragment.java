package com.maternity.muaiwork.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.andbase.library.app.base.AbBaseFragment;
import com.andbase.library.util.AbToastUtil;
import com.andbase.library.view.listener.AbOnClickListener;
import com.google.gson.JsonObject;
import com.maternity.muaiwork.dialog.DialogLoading;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by apple on 2017/2/7.
 */

public class BaseFragment extends AbBaseFragment {
    public int fragWidth,fragHight;
    public SendCommandInterface activityCallback;
    public BaseFragment() {
        super();
    }


//    public static BaseFragment initBaseFragment(SendCommandInterface callback) {
//       // this.activityCallback = callback;
////        WindowManager wm = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
////        //getSystemService(Context.WINDOW_SERVICE);
////        DisplayMetrics metrics=new DisplayMetrics();
////        wm.getDefaultDisplay().getMetrics(metrics);
////        fragWidth = metrics.widthPixels;   //getDefaultDisplay().getWidth();
////        fragHight = metrics.heightPixels;
//        BaseFragment baseFragment=new BaseFragment();
//        baseFragment.activityCallback=callback;
//        return baseFragment;
//    }


    public void setActivityCallback(SendCommandInterface activityCallback) {
        this.activityCallback = activityCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WindowManager wm = (WindowManager)getActivity().getSystemService(Context.WINDOW_SERVICE);
        //getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        fragWidth = metrics.widthPixels;   //getDefaultDisplay().getWidth();
        fragHight = metrics.heightPixels;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initData() {

    }
    DialogLoading loadDialog;
    //AbLoadDialogFragment loadDialog;
    protected void showToast(String msg){
//        AbToastUtil.showToast(this, msg);
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
    protected void showDialog2Btn(String title, String msg, AbOnClickListener shure){
//        AbDialogUtil.showAlertDialog(this, R.drawable.logo, title, msg, shure);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void showLoading(int icoId, String msg)
    {
        loadDialog = new DialogLoading(getActivity()," ",msg,0);
        loadDialog.show();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void sendPost(final String address, final JsonObject json, String loadmsg, final int tag)
    {
        showLoading(0,loadmsg);
        RequestParams params=new RequestParams(address);
        params.addBodyParameter("data",json.toString());
        x.http().post(params,new Callback.CommonCallback<String>(){
            @Override
            public void onCancelled(CancelledException cex) {
//                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
                //showToast("网络通讯终止!");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
                showToast("网络异常！");
            }

            @Override
            public void onFinished() {
//                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
                //showToast("网络通讯结束!");
            }

            @Override
            public void onSuccess(String result) {
//                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
                getResult(result,tag);
            }
        });
//        loadDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialogInterface) {
//                loadDialog.loadFinish();
//                showToast("载入终止....");
//            }
//        });

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void sendFile(final String address, final String path, final String fileKey, String loadmsg, final int tag)
    {
        showLoading(0,loadmsg);
        RequestParams params=new RequestParams(address);
        params.setMultipart(true);
        params.addBodyParameter(fileKey,new File(path));

        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onCancelled(CancelledException cex) {
                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
                showToast(ex.toString());
            }

            @Override
            public void onFinished() {
                if (!loadDialog.isShowing())
                    loadDialog.dismiss();

            }

            @Override
            public void onSuccess(String result) {
                if (!loadDialog.isShowing())
                    loadDialog.dismiss();
                getResult(result,tag);
            }
        });
    }
    protected void getResult(String result,int tag)
    {
        //网络接收返回
    }
    public interface SendCommandInterface
    {
        public void sendCommand(String msg);
    }

    public void reflashFragment()
    {

    }
}

