package com.maternity.muaiwork.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.andbase.library.app.base.AbBaseActivity;
import com.andbase.library.util.AbDialogUtil;
import com.andbase.library.util.AbToastUtil;
import com.andbase.library.view.dialog.AbAlertDialogFragment;
import com.andbase.library.view.dialog.AbProgressDialogFragment;
import com.andbase.library.view.listener.AbOnClickListener;
import com.google.gson.JsonObject;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.common.PubConnection;
import com.maternity.muaiwork.dialog.DialogLoading;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;

/**
 * Created by apple on 16/10/14.
 * 百度统计key：369cab6e64
 */

public class CBaseActivity extends AppCompatActivity {

    public static int IMAGE_TARGET_LIB;//100
    public static int IMAGE_TARGET_CAM;//200
    public static int VIDEO_TARGET_LIB;//300
    public static int VIDEO_TARGET_CAM;//400
    public String tempPath;//sdCardPath
    public String tempFile;//临时文件
    public int screenWidth=0;
    public int screenHight=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tempPath=Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ? Environment
                .getExternalStorageDirectory().getPath() : Environment
                .getDownloadCacheDirectory().getPath();
        tempPath=tempPath+"/temp";
        tempFile=tempPath+"/imageTemp.jpg";
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        //getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;   //getDefaultDisplay().getWidth();
        screenHight = metrics.heightPixels;
//        WindowManager wm=this.getWindowManager();
//        Point p=new Point();
//        wm.getDefaultDisplay().getSize(p);
//        screenWidth=p.x;
//        screenHight=p.y;

        File f=new File(tempPath);
        if(!f.exists())
        {
            f.mkdir();
        }
        File img=new  File(tempFile);
        if (!img.exists())
        {
            try {
                img.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Log.e("chester",tempFile);

        initData();
    }

    private void initData() {

    }
    DialogLoading loadDialog;
    //AbLoadDialogFragment loadDialog;
    protected void showToast(String msg){
        AbToastUtil.showToast(this, msg);
    }
    protected void showDialog2Btn(String title, String msg, AbOnClickListener shure){
//        AbDialogUtil.showAlertDialog(this, R.drawable.logo, title, msg, shure);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void showLoading(int icoId, String msg)
    {
        loadDialog = new DialogLoading(this,"",msg,0);
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
                if (loadDialog.isShowing())
                    loadDialog.dismiss();
                //showToast("网络通讯终止!");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (loadDialog.isShowing())
                    loadDialog.dismiss();
                showToast("网络异常");
            }

            @Override
            public void onFinished() {
                if (loadDialog.isShowing())
                    loadDialog.dismiss();
                //showToast("网络通讯结束!");
            }

            @Override
            public void onSuccess(String result) {
                if (loadDialog.isShowing())
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
    //文件获取
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
                if (loadDialog.isShowing())
                    loadDialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (loadDialog.isShowing())
                    loadDialog.dismiss();
                showToast("网络通讯异常！");
            }

            @Override
            public void onFinished() {
                if (loadDialog.isShowing())
                    loadDialog.dismiss();

            }

            @Override
            public void onSuccess(String result) {
                if (loadDialog.isShowing())
                    loadDialog.dismiss();
                getResult(result,tag);
            }
        });
    }
    protected void getResult(String result,int tag)
    {
        //网络接收返回
    }


    //////////////////////////////////////
    //相册获取照片
    protected void imgFromPhoto()
    {
        File file=new File(tempFile);
        if (file.exists())
        {
            file.delete();
        }
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_TARGET_LIB);
    }
    //相机获取照片
    protected void imgFromCamer()
    {
        File file=new File(tempFile);
        if (file.exists())
        {
            file.delete();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.setType("image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", 500);
//        intent.putExtra("outputY", 500);
//        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile( new File(tempFile)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, IMAGE_TARGET_CAM);
    }
    //相册获取视频
    protected void vidFromPhoto()
    {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);

        innerIntent.setType("video/*"); //String VIDEO_UNSPECIFIED = "video/*";

        Intent wrapperIntent = Intent.createChooser(innerIntent, null);

        startActivityForResult(wrapperIntent, VIDEO_TARGET_LIB);

    }
    //摄像头拍摄视频
    protected void vidFromCamer()
    {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(tempFile));

        // set the video image quality to high
        takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0.6);
        startActivityForResult(takeVideoIntent,VIDEO_TARGET_CAM);
    }

    protected String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}
