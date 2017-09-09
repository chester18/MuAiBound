package com.maternity.muaiwork.activity.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import com.maternity.muaiwork.model.CertificateModel;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.TrueInfoModel;
import com.maternity.muaiwork.view.ButtonCell;
import com.maternity.muaiwork.view.IcoCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * Created by apple on 16/10/14.
 */
@ContentView(R.layout.activity_nomal_list)
public class DetailCertificateActivity extends CBaseActivity {
    @ViewInject(R.id.list_title)
    TextView title;
    @ViewInject(R.id.nomal_list)
    ListView lv;
    @ViewInject(R.id.list_add)
    IconTextView itv;

    CertificateModel model;            //数据模型
    String updataUrl;               //上传地址
    String[] mainTitle={"发证机构:","证书名称:","等级:","发证日期:","正面照:","内容照:",""};
    String[] keys={"Organization","CertificateName","CertificateGrade","CertificateDate","CertificateFaceImage","CertificateContentImg"};
    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return mainTitle.length;
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
            if (i==mainTitle.length-1)
            {
                ButtonCell bcell=new ButtonCell(DetailCertificateActivity.this);
                bcell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.MATCH_PARENT));
                return bcell;
            }
            IcoCell cell=new IcoCell(DetailCertificateActivity.this);

                JsonObject mj = model.getJsonObject();
                if (PubFunction.hasJsonObject(mj, keys[i])) {
                    cell.setModel(new DetailCellModel(null, mainTitle[i], model.getJsonObject().get(keys[i]).getAsString(), null, "{md-chevron-right}"));
                }else {
                    cell.setModel(new DetailCellModel(null, mainTitle[i], "", null, "{md-chevron-right}"));
                }
                cell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));

            cell.setBackgroundResource(R.color.CW);
            return cell;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        itv.setVisibility(View.INVISIBLE);
        model= (CertificateModel) getIntent().getSerializableExtra("model");
        if (model==null)
        {
            model=new CertificateModel(new JsonObject());
            model.userId= PubFunction.userid;
            updataUrl=CommonNetString.userAddCertificate;
        }else {
            updataUrl=CommonNetString.userUpdateCertificate;
        }
        title.setText("证    书");
        lv.setAdapter(adapter);


    }
    @Event(value = R.id.backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }

    @Event(value = R.id.nomal_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        int tag=position;
        switch (position){
            case 0:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 0, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.Organization=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 1:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 1, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.CertificateName=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 2:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 2, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.CertificateGrade=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 3:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_DATE, 3, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.CertificateDate=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 4:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_IMAGE, 4, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        IMAGE_TARGET_CAM=200+4;
                        imgFromCamer();

                    }

                    @Override
                    public void selectCancle(int tag) {
                        IMAGE_TARGET_LIB=100+4;
                        imgFromPhoto();
                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }
            case 5:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_IMAGE, 5, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        IMAGE_TARGET_CAM=200+5;
                        imgFromCamer();
                    }

                    @Override
                    public void selectCancle(int tag) {
                        IMAGE_TARGET_LIB=100+5;
                        imgFromPhoto();
                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                bd.show();
                break;
            }

            default:
                sendPost(updataUrl,model.getJsonObject(),"信息提交中...",100);
                break;

        }

    }

    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==4)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                JsonObject img=jo.getAsJsonObject("data");
                model.CertificateFaceImage=img.get("name").getAsString();
                adapter.notifyDataSetChanged();

            }else {
                showToast(jo.get("msg").getAsString());
            }
        }
        if (tag==5)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                JsonObject img=jo.getAsJsonObject("data");
                model.CertificateContentImg=img.get("name").getAsString();
                adapter.notifyDataSetChanged();
            }else {
                showToast(jo.get("msg").getAsString());
            }
        }
        if(tag==100)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                showToast("更新完成!");
                finish();
            }else {
                showToast("网络请求失败!");
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        File f=new File(tempFile);
        int tag=-1;
        if (requestCode==IMAGE_TARGET_CAM)
        {
            tag=IMAGE_TARGET_CAM-200;
        }
        if (requestCode==IMAGE_TARGET_LIB)
        {
            tag=IMAGE_TARGET_LIB-100;
        }

        if (requestCode == IMAGE_TARGET_CAM) {
            sendFile(CommonNetString.uploadImage, tempFile, "image", "上传照片", tag);
        } else if (requestCode == IMAGE_TARGET_LIB) {
            Uri uri = data.getData();
            if (uri != null) {
                sendFile(CommonNetString.uploadImage, getRealPathFromURI(uri), "image", "上传照片", tag);
            }
        }
//        sendFile(CommonNetString.uploadImage,tempFile,"image","上传照片",tag);
    }
}
