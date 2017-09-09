package com.maternity.muaiwork.activity.work;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.andview.refreshview.XRefreshView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.activity.user.DetailCertificateActivity;
import com.maternity.muaiwork.common.CellCallBack;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;
import com.maternity.muaiwork.dialog.DialogCallBackInterface;
import com.maternity.muaiwork.dialog.DialogFactry;
import com.maternity.muaiwork.dialog.DialogSimpleList;
import com.maternity.muaiwork.model.DetailCellModel;
import com.maternity.muaiwork.model.WorkHistoryModel;
import com.maternity.muaiwork.model.WorkItemModel;
import com.maternity.muaiwork.view.DetailCell;
import com.maternity.muaiwork.view.WorkRecorderCell;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by apple on 2017/3/2.
 */
@ContentView(R.layout.activity_study_list)
public class WorkRecorderActivity extends CBaseActivity implements CellCallBack {
    @ViewInject(R.id.stud_title_view)
    TextView headview;
    @ViewInject(R.id.stud_submit)
    TextView submitText;
    @ViewInject(R.id.stud_custom_view)
    XRefreshView refreshView;
    @ViewInject(R.id.stud_tpoic_list)
    ListView topicList;
    public static long lastRefreshTime=0;
    BaseAdapter baseAdapter=new BaseAdapter(){

        @Override
        public int getCount() {
            return sourceSet.size();
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
            WorkHistoryModel model=dataMap.get(sourceSet.get(i));
            WorkRecorderCell detailCell=new WorkRecorderCell(WorkRecorderActivity.this);
//            String[] sd= (String[]) sourceSet.toArray();
            detailCell.setModelWithIndex(model,i);
//            String value= dataMap.get(sourceSet.get(i)).workRecordListValue==null?"暂无": (String) dataMap.get(sourceSet.get(i)).workRecordListValue;
//            detailCell.setModel(new DetailCellModel(null,null,sourceSet.get(i),value));
            detailCell.setCallBack(WorkRecorderActivity.this);
            //detailCell.setLayoutParams(new AbsListView.LayoutParams((int) (screenWidth*1.5),-2));
            return detailCell;
        }
    };
    Map<String,List<String>> dataSource =new HashMap<String, List<String>>();//记录数据源
    Map<String,WorkHistoryModel> dataMap=new HashMap<String, WorkHistoryModel>(); //记录值
    List<String> sourceSet=new ArrayList<>();//关键值
    int workType=0;
    int serverId=0;
    List<WorkItemModel> itemsSource=new ArrayList<WorkItemModel>();
    int page=1;
    int size=30;
    int photoSelect=-1;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        dataSource=new HashMap<String, List<String>>();
//        dataMap=new HashMap<String, String>();
        workType=getIntent().getIntExtra("workType",0);
        serverId=getIntent().getIntExtra("serverId",0);
        initData();
        topicList.setAdapter(baseAdapter);
        refreshView.setPullLoadEnable(false);
        refreshView.setPullRefreshEnable(false);
        submitText.setText("提交");
        submitText.setClickable(true);


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initData()
    {
        if (dataSource.isEmpty())
        dataSource=new HashMap<String, List<String>>();
        if (dataMap.isEmpty())
        dataMap=new HashMap<String, WorkHistoryModel>();
//        sourceSet=dataSource.keySet();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("workOrderId",serverId);
        jsonObject.addProperty("pageNumber",page);
        jsonObject.addProperty("pageSize",size);
        if (workType==900001) {
            sendPost(CommonNetString.selectRecordersMom,jsonObject,"读取今日记录选项",100);

            headview.setText("妈妈工作记录");
        }
        if (workType==900002){
            sendPost(CommonNetString.selectRecordersChild,jsonObject,"读取今日记录选项",200);
            headview.setText("宝宝工作记录");
        }


    }

    @Event(value = R.id.stud_detail_backICO,type = View.OnClickListener.class)
    private void backClick(View v)
    {
        finish();
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Event(value = R.id.stud_submit,type = View.OnClickListener.class)
            private void sub(View v)
    {
        submitData();
    }



    @Event(value = R.id.stud_tpoic_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id) {
//        String[] sd= (String[]) sourceSet.toArray();
        List<String> selectString=dataSource.get(sourceSet.get(position));
        int seleType=itemsSource.get(position).workRecordItemType;
        if (selectString!=null) {

//            if (seleType==900001)
            DialogSimpleList dsl = (DialogSimpleList) DialogFactry.instance(WorkRecorderActivity.this, DialogFactry
                            .DIA_LIST, position,
                    sourceSet.get(position),
                    null, new DialogCallBackInterface() {
                        @Override
                        public void selectShure(int tag, Object result) {

                        }

                        @Override
                        public void selectCancle(int tag) {

                        }

                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void selectIndex(int tag, int index, Object object) {
//                        String[] bas= (String[]) sourceSet.toArray();
                            WorkHistoryModel whm = dataMap.get(sourceSet.get(tag));
                            whm.workRecordListValue = object.toString();
                            whm.workRecordListDay = PubFunction.getTodayAtg();
                            whm.workRecordListDate = PubFunction.getTodayTime();
                            dataMap.put(sourceSet.get(tag), whm);

                            baseAdapter.notifyDataSetChanged();

                        }
                    });
            String[] selkeys = (String[]) selectString.toArray();
            dsl.setStrings(selkeys);
            dsl.show();
        }else {
            showToast("暂无选项");
        }


    }
    ////////////////////////////////////////////////////////////////////////

    /**
     * 请求填写数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void requestKey()
    {
        int workRecordItemType=workType;
        JsonObject jo=new JsonObject();
        jo.addProperty("workRecordItemType",workRecordItemType);
        sendPost(CommonNetString.getRecordInfo,jo,"获取工作选项...",100);
    }

    /**
     * 提交填写数据
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void submitData()
    {
        Set<String> setKey=dataMap.keySet();
//        String[] keys= (String[]) setKey.iterator();
        JsonArray array=new JsonArray();
        for (Iterator it=setKey.iterator();it.hasNext();)
        {
            WorkHistoryModel model=dataMap.get(it.next().toString());
            if (!model.workRecordListValue.equals(""))
            array.add(historyMark(model).getJsonObject());
        }
        JsonObject jo=new JsonObject();
        jo.add("list",array);

        sendPost(CommonNetString.addRecorders,jo,"提交工作记录...",300);
    }
    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100||tag==200)
        {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            int status=root.get("result").getAsInt();
            if (status==1)
            {
                JsonArray items=root.getAsJsonArray("data");
                for (int i=0;i<items.size();i++)
                {
                    WorkItemModel whm=new WorkItemModel(items.get(i).getAsJsonObject());
                    sourceSet.add(whm.workRecordItemName);
                    WorkHistoryModel wHistory=new WorkHistoryModel();
                    wHistory.workRecordItemId=whm.pid;
                    wHistory.workRecordItemName=whm.workRecordItemName;
                    wHistory.orderListId=PubFunction.workOrderId;
                    wHistory.workerId=PubFunction.userid;
                    wHistory.workRecordType=workType;
                    wHistory.workServerId=serverId;
                    dataMap.put(whm.workRecordItemName,wHistory);
                    if (whm.workRecordItemSelect!=null)
                    {
                        String[] rs=whm.workRecordItemSelect.split("@");
                        List<String> array= Arrays.asList(rs);
                        dataSource.put(whm.workRecordItemName,array);
                    }
                    itemsSource.add(whm);


                }
                baseAdapter.notifyDataSetChanged();

            }else {
                showToast(root.get("msg").getAsString());
            }

        }else if (tag==300)
        {
            JsonObject root=new JsonParser().parse(result).getAsJsonObject();
            int status=root.get("result").getAsInt();
            if (status==1)
            {
                showToast("工作提交完成！");
                finish();
            }else {
                showToast(root.get("msg").getAsString());
            }
        }else if (tag==500)
        {
            //照片文件返回
            JsonObject jo= new JsonParser().parse(result).getAsJsonObject();
            if (jo.get("result").getAsInt()==1)
            {
                JsonObject img=jo.getAsJsonObject("data");
                String fname=img.get("name").getAsString();
                WorkHistoryModel hm=dataMap.get(sourceSet.get(photoSelect));
                hm.addPhoto(fname);
                dataMap.put(sourceSet.get(photoSelect),hm);
                baseAdapter.notifyDataSetChanged();

            }else {
                showToast(jo.get("msg").getAsString());
            }
        }


    }

    /**
     * 打本地信息标签
     * @param model
     * @return
     */
    private WorkHistoryModel historyMark(WorkHistoryModel model)
    {
        model.orderListId= PubFunction.workOrderId;
        model.workerId=PubFunction.userid;
        model.workRecordType=workType;
        model.workServerId=serverId;

        return model;
    }

    @Override
    public void cellCallBackFromIndex(int index, Object object) {
        String[] keys=new String[]{"相册选择照片","现场拍摄照片"};
        photoSelect=index;
        DialogSimpleList dsl = (DialogSimpleList) DialogFactry.instance(WorkRecorderActivity.this, DialogFactry.DIA_LIST,index,"选择获取照片方式",null,new DialogCallBackInterface(){

            @Override
            public void selectShure(int tag, Object result) {

            }

            @Override
            public void selectCancle(int tag) {

            }

            @Override
            public void selectIndex(int tag, int index, Object object) {

                if (index==0)
                {
                    imgFromPhoto();
                }
                if (index==1)
                {
                    imgFromCamer();
                }
            }
        });
        dsl.setStrings(keys);
        dsl.show();

    }
    //获取照片返回

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        File f=new File(tempFile);
//        if (requestCode == IMAGE_TARGET_CAM) {
//            sendFile(CommonNetString.uploadImage, tempFile, "image", "上传照片", 500);
//        } else if (requestCode == IMAGE_TARGET_LIB) {
//            Uri uri = data.getData();
//            if (uri != null) {
//                sendFile(CommonNetString.uploadImage, getRealPathFromURI(uri), "image", "上传照片", 500);
//            }
//        }
        if(resultCode == RESULT_OK)
        if (data!=null) {
            Uri uri = null;
            try {


                uri = data.getData();
            } catch (Exception e) {
                e.printStackTrace();

            }
            if (uri == null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    showToast("获取照片有误！");
                    return;
                } else {
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    try {
                        BufferedOutputStream bos = new BufferedOutputStream(
                                new FileOutputStream(tempFile, false));
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                        bos.flush();
                        bos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToast("图片获取有误");
                    }

                }
            } else {
                tempFile = getRealPathFromURI(uri);
            }
        }
        sendFile(CommonNetString.uploadImage,tempFile,"image","上传照片",500);
    }
}
