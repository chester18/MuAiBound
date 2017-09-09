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
import com.maternity.muaiwork.dialog.DialogSimpleList;
import com.maternity.muaiwork.dialog.MulitSelectDialog;
import com.maternity.muaiwork.model.BaseModel;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.TrainingModel;
import com.maternity.muaiwork.model.TrueInfoModel;
import com.maternity.muaiwork.view.ButtonCell;
import com.maternity.muaiwork.view.IcoCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by apple on 16/10/14.
 */
@ContentView(R.layout.activity_nomal_list)
public class DetailUserInfoActivity extends CBaseActivity {
    @ViewInject(R.id.list_title)
    TextView title;
    @ViewInject(R.id.nomal_list)
    ListView lv;
    @ViewInject(R.id.list_add)
    IconTextView itv;
    TrueInfoModel model;            //数据模型
    String updataUrl;               //上传地址
    String[] mainTitle={"姓名:","身份证:","身份证照片:","手机号:","出生日期:","年    龄:","籍    贯:","属    相:","身    高:","学    历:","婚育状况:","你想做:","服务年限:","服务家庭数:","薪水范围:","获得证书:","技    能:"};
    String[] keys={"xingmin","cardId","cardIdImg","mobile","birthday","age","jiguan","shuxiang","hight","study","married","site","workExperience","homeCount","salary","certificate","tech"};
    BaseAdapter adapter=new BaseAdapter() {
        @Override
        public int getCount() {
            if (isEdit)
            return mainTitle.length+1;
            else
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
            if (i == mainTitle.length) {
                ButtonCell bcell = new ButtonCell(DetailUserInfoActivity.this);
                bcell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
                return bcell;
            }
            IcoCell cell = new IcoCell(DetailUserInfoActivity.this);
            JsonObject mj = model.getJsonObject();
            if (PubFunction.hasJsonObject(mj, keys[i])) {
                String value = model.getJsonObject().get(keys[i]).getAsString();
                cell.setModel(new DetailCellModel(null, mainTitle[i], value, null, "{md-chevron-right}"));
            }else {
                cell.setModel(new DetailCellModel(null, mainTitle[i], "", null, "{md-chevron-right}"));
            }
            cell.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));

            cell.setBackgroundResource(R.color.CW);
            return cell;

        }
    };
    Map<String,String> JG=new HashMap<String, String>();
    String[] workKinds={"月嫂","育儿嫂","月嫂、育儿嫂均可"};
    String[] workYear={"一年以内","一~二年","二~五年","五年以上"};
    String[] marrs={"已婚已育","离异","已婚未育","未婚未育"};
    String[] techs={"月子餐","辅食","开奶","儿歌","故事","英语","产妇按摩","产妇心理疏导","抚触","早教","生活习惯培养","新生儿护理"};
    String[] cert={"母婴护理证","健康证","教师资格证","护照","护士证","育婴师证(初级)","育婴师证(中级)","育婴师证(高级)","护士证","小儿推拿证","驾驶证","催乳师证(初级)","催乳师证(高级)","按摩证","早教证","营养师证"};
    String[] salarys={"5000以下","5000起","6000起","7000起","8000起","9000起","10000起"};

    boolean isEdit=true;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        isEdit=getIntent().getBooleanExtra("isEdit",true);
        itv.setVisibility(View.INVISIBLE);
        model= (TrueInfoModel) getIntent().getSerializableExtra("model");
        if (model==null)
        {
            model=new TrueInfoModel(new JsonObject());
            model.userId=PubFunction.userid;
            model.isverifyStatus=400001;
        }
        title.setText("个人信息");
        lv.setAdapter(adapter);
        //读取信息
        JsonObject jo=new JsonObject();
        jo.addProperty("userid", PubFunction.userid);
        sendPost(CommonNetString.userReadInfo,jo,"读取信息中...",0);

        JG.put("11",getString(R.string.a11));
        JG.put("12",getString(R.string.a12));
        JG.put("13",getString(R.string.a13));
        JG.put("14",getString(R.string.a14));
        JG.put("15",getString(R.string.a15));
        JG.put("21",getString(R.string.a21));
        JG.put("22",getString(R.string.a22));
        JG.put("23",getString(R.string.a23));
        JG.put("31",getString(R.string.a31));
        JG.put("32",getString(R.string.a32));
        JG.put("33",getString(R.string.a33));
        JG.put("34",getString(R.string.a34));
        JG.put("35",getString(R.string.a35));
        JG.put("36",getString(R.string.a36));
        JG.put("37",getString(R.string.a37));
        JG.put("41",getString(R.string.a41));
        JG.put("42",getString(R.string.a42));
        JG.put("43",getString(R.string.a43));
        JG.put("44",getString(R.string.a44));
        JG.put("45",getString(R.string.a45));
        JG.put("46",getString(R.string.a46));
        JG.put("50",getString(R.string.a50));
        JG.put("51",getString(R.string.a51));
        JG.put("52",getString(R.string.a52));
        JG.put("53",getString(R.string.a53));
        JG.put("54",getString(R.string.a54));
        JG.put("61",getString(R.string.a61));
        JG.put("62",getString(R.string.a62));
        JG.put("63",getString(R.string.a63));
        JG.put("64",getString(R.string.a64));
        JG.put("65",getString(R.string.a65));

    }
    @Event(value = R.id.backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.nomal_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (!isEdit) return;
        int tag=position;
        switch (position){
            case 0:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 0, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
//                        showToast(result.toString());
                        model.xingmin=result.toString();
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
                        model.cardId=result.toString();
                        if(model.cardId.length()==18)
                        {
                            //省份
                            String sf=model.cardId.substring(0,2);
                            model.jiguan=JG.get(sf);
                            //出生年月 31023 01976 05283 116
                            String y=model.cardId.substring(6,10);
                            String m=model.cardId.substring(10,12);
                            String d=model.cardId.substring(12,14);
                            model.birthday=y+"-"+m+"-"+d;
                            //年龄
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
                            Date dt=new Date();
                            String tsd=sdf.format(dt);
                            model.age=Integer.parseInt(tsd)-Integer.parseInt(y);
                            //属相
                            int sx=(Integer.parseInt(y)-4)%12;
                            String[]sxList=getResources().getStringArray(R.array.Zodiac);
                            model.shuxiang=sxList[sx];
                        }else {
                            showToast("身份证号不是二代身份证,请从新输入!");
                            return;
                        }
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
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_IMAGE, 2, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        IMAGE_TARGET_CAM=200+2;
                        imgFromCamer();
                    }

                    @Override
                    public void selectCancle(int tag) {
                        IMAGE_TARGET_LIB=100+2;
                        imgFromPhoto();
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
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 3, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.mobile=result.toString();
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
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_DATE, 4, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.birthday=result.toString();
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
            case 5:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 5, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.age=Integer.parseInt(result.toString());
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
            case 6:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_LIST, 6, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.jiguan=result.toString();
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {
                        model.jiguan=object.toString();
                        adapter.notifyDataSetChanged();
                    }
                });
                ((DialogSimpleList)bd).setStrings(getResources().getStringArray(R.array.sheng));
                bd.show();
                break;
            }
            case 7:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_LIST, 7, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
//                        model.shuxiang=result.toString();
//                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {
                        model.shuxiang=object.toString();
                        adapter.notifyDataSetChanged();
                    }
                });
                ((DialogSimpleList)bd).setStrings(getResources().getStringArray(R.array.Zodiac));
                bd.show();
                break;
            }
            case 8:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 8, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.hight=Float.parseFloat(result.toString());
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
            case 9:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_LIST, 9, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
//                        model.study=result.toString();
//                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {
                        model.study=object.toString();
                        adapter.notifyDataSetChanged();
                    }
                });
                ((DialogSimpleList)bd).setStrings(new String[]{"高中","初中","中专","大专","小学"});
                bd.show();
                break;
            }
            case 10:
            {
                BaseDialog bd=DialogFactry.instance(this,DialogFactry.DIA_LIST,position,mainTitle[position],"",new DialogCallBackInterface(){
                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {
                        model.married=object.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectShure(int tag, Object result) {

                    }
                });
                ((DialogSimpleList)bd).setStrings(marrs);
                bd.show();
                break;
            }
            case 11:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_LIST, position, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.site=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                        model.site=object.toString();
                        adapter.notifyDataSetChanged();
                    }
                });
                ((DialogSimpleList)bd).setStrings(workKinds);
                bd.show();
                break;
            }
            case 12:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_LIST, position, mainTitle[position], "主要是您从事该职业多少年，带过多少宝宝等。", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.workExperience=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {
                        model.workExperience=object.toString();
                        adapter.notifyDataSetChanged();
                    }
                });
                ((DialogSimpleList)bd).setStrings(workYear);
                bd.show();
                break;
            }
            case 13:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_INPUT, 3, mainTitle[position], "服务家庭数量", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.homeCount=Integer.parseInt(result.toString());
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
            case 14:
            {
                BaseDialog bd= DialogFactry.instance(this, DialogFactry.DIA_LIST, position, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.salary=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {
                        model.salary=object.toString();
                        adapter.notifyDataSetChanged();
                    }
                });
                ((DialogSimpleList)bd).setStrings(salarys);
                bd.show();
                break;
            }
            case 15:
            {
                BaseDialog bd=DialogFactry.instance(this, DialogFactry.DIA_MULIT, position, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.certificate=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                ((MulitSelectDialog)bd).setSelectItems(cert);
                bd.show();
                break;
            }
            case 16:
            {
                BaseDialog bd=DialogFactry.instance(this, DialogFactry.DIA_MULIT, position, mainTitle[position], "", new DialogCallBackInterface() {
                    @Override
                    public void selectShure(int tag, Object result) {
                        model.tech=result.toString();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void selectCancle(int tag) {

                    }

                    @Override
                    public void selectIndex(int tag, int index, Object object) {

                    }
                });
                ((MulitSelectDialog)bd).setSelectItems(techs);
                bd.show();
                break;
            }

            default:
                sendPost(CommonNetString.userUpdateInfo,model.getJsonObject(),"信息提交中...",1);
                break;

        }
        
    }
    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==0)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                model=new TrueInfoModel(jo.getAsJsonObject("data"));
                adapter.notifyDataSetChanged();


            }else {
                showToast("网络请求失败!");
            }
        }
        if (tag==1)
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
        if(tag==2)
        {
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                JsonObject img=jo.getAsJsonObject("data");
                model.cardIdImg=img.get("name").getAsString();
                adapter.notifyDataSetChanged();
            }else {
                showToast(jo.get("msg").getAsString());
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        File f = new File(tempFile);
        if (requestCode == IMAGE_TARGET_CAM) {
            sendFile(CommonNetString.uploadImage, tempFile, "image", "上传身份证照片", 2);
        } else if (requestCode == IMAGE_TARGET_LIB) {
            Uri uri = data.getData();
            if (uri != null) {
                sendFile(CommonNetString.uploadImage, getRealPathFromURI(uri), "image", "上传身份证照片", 2);
            }
        }
    }
}
