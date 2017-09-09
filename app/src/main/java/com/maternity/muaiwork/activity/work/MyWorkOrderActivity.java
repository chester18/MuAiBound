package com.maternity.muaiwork.activity.work;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maternity.muaiwork.R;
import com.maternity.muaiwork.activity.CBaseActivity;
import com.maternity.muaiwork.activity.study.MapDictionaryActivity;
import com.maternity.muaiwork.common.CellCallBack;
import com.maternity.muaiwork.common.CommonNetString;
import com.maternity.muaiwork.common.PubFunction;

import com.maternity.muaiwork.model.LocationModel;
import com.maternity.muaiwork.model.WorkOrderModel;
import com.maternity.muaiwork.view.WorkOrderCell;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2017/6/26.
 * 我的工单
 */
@ContentView(R.layout.activity_study_list)
public class MyWorkOrderActivity extends CBaseActivity implements CellCallBack {
    @ViewInject(R.id.stud_title_view)
    TextView title;
    @ViewInject(R.id.stud_tpoic_list)
    ListView listView;
    @ViewInject(R.id.stud_custom_view)
    XRefreshView refreshView;
    public static long lastRefreshTime=0;
    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return historyList.size();
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
            WorkOrderModel tm=historyList.get(i);
            WorkOrderCell cell=new WorkOrderCell(MyWorkOrderActivity.this);
            cell.setModelWithIndex(tm,i);
//            cell.setModel(new DetailCellModel(null,null,tm.momName,tm.startTime));
            cell.callBack=MyWorkOrderActivity.this;
            cell.setLayoutParams(new AbsListView.LayoutParams((int) (screenWidth), -2));
//                view=cell;
            return cell;


        }
    };
    int page=0;
    int size=20;
    int status=0;
    List<WorkOrderModel> historyList=new ArrayList<WorkOrderModel>();

    //地理位置
    LocationClient mLocationClient=null;
    BDAbstractLocationListener myListener=new BDAbstractLocationListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location!=null)
            {
                locationModel.locationTime= location.getTime();    //获取定位时间
                locationModel.locationId=location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
                locationModel.locationType=location.getLocType();    //获取定位类型
                locationModel.locationLatitude=location.getLatitude();    //获取纬度信息
                locationModel.locationLongitude=location.getLongitude();    //获取经度信息
                locationModel.locationRadius=location.getRadius();    //获取定位精准度
                locationModel.locationAddrStr=location.getAddrStr();    //获取地址信息
                locationModel.locationCountry=location.getCountry();    //获取国家信息
                locationModel.locationCountryCode=location.getCountryCode();    //获取国家码
                locationModel.locationCity=location.getCity();    //获取城市信息
                locationModel.locationCityCode=location.getCityCode();    //获取城市码
                locationModel.locationDistrict=location.getDistrict();    //获取区县信息
                locationModel.locationStreet=location.getStreet();    //获取街道信息
                locationModel.locationStreetNumber=location.getStreetNumber();    //获取街道码
                locationModel.locationLocationDescribe=location.getLocationDescribe();    //获取当前位置描述信息
                locationModel.locationPoiList=location.getPoiList();    //获取当前位置周边POI信息

                locationModel.locationBuildingID=location.getBuildingID();    //室内精准定位下，获取楼宇ID
                locationModel.locationBuildingName=location.getBuildingName();    //室内精准定位下，获取楼宇名称
                locationModel.locationFloor=location.getFloor();

                JsonObject jo=new JsonObject();
                jo.addProperty("workId",PubFunction.userid);
                jo.addProperty("longitude",locationModel.locationLongitude);
                jo.addProperty("latitude",locationModel.locationLatitude);
                jo.addProperty("orderId",currentModel.pid);
                if (currentModel!=null) {
                    String url="";
                    if (currentModel.workOderStatus==900003)
                    {
                        url=CommonNetString.Single;
                    }else {
                        url=CommonNetString.PlaceOrder;
                    }

                    sendPost(url, jo, "上单中...", 300);
                    mLocationClient.stop();
                }
            }
        }
    };
    WorkOrderModel currentModel;
    LocationModel locationModel;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        listView.setAdapter(baseAdapter);
        title.setText("我的工单");
        loadData();
        initRefresh();
        initLocation();
    }

    private void initLocation()
    {
        //初始化定位信息
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

//        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadData()
    {
        JsonObject jo=new JsonObject();
//        jo.addProperty("pageNumber",page);
//        jo.addProperty("pageSize",size);

        jo.addProperty("workId", PubFunction.userid);
        sendPost(CommonNetString.getWorkOderList,jo,"获取工作记录...",100);
    }
    private void initRefresh()
    {
        refreshView.setPullLoadEnable(false);
        refreshView.restoreLastRefreshTime(lastRefreshTime);
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {


            @Override
            public void onRefresh() {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onRefresh(boolean isPullDown) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        refreshView.stopRefresh();          //停止下拉刷新UI
//                        lastRefreshTime = refreshView.getLastRefreshTime();
//                    }
//                }, 5000);
                historyList.clear();
                status=1;
                loadData();

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLoadMore(boolean isSilence) {
                //
//                status=2;
//                loadData();
                refreshView.stopLoadMore();

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void getResult(String result, int tag) {
        super.getResult(result, tag);
        if (tag==100) {
            JsonObject object = new JsonParser().parse(result).getAsJsonObject();
//        showToast(object.get("msg").toString());
            JsonArray user = object.getAsJsonArray("data");
            for (int i = 0; i < user.size(); i++)
                historyList.add(new WorkOrderModel(user.get(i).getAsJsonObject()));
            baseAdapter.notifyDataSetChanged();
            refreshView.stopRefresh();

        }
        if (tag==300)
        {
            JsonObject object = new JsonParser().parse(result).getAsJsonObject();
            if (object.get("result").getAsInt()>0)
            {
                showToast("上单完成");
                historyList.clear();
                status=1;
                loadData();
            }
        }
    }

    @Event(value = R.id.stud_tpoic_list, type = AdapterView.OnItemClickListener.class)
    private void listViewClick(AdapterView<?> parent, View view, int position, long id)
    {
        //查看信息
        WorkOrderModel model=historyList.get(position);
        if (model.workOderStatus<900002)
        {
            showToast("未领用设备，无权查看信息！");
        }else {
            Intent intent=new Intent(MyWorkOrderActivity.this, MapDictionaryActivity.class);
            Map<String,String> dictionary=model.getDictionary();
            intent.putExtra("data", (Serializable) dictionary);
            startActivity(intent);

        }

    }
//stud_detail_backICO
    @Event(value = R.id.stud_detail_backICO,type = View.OnClickListener.class)
    private void backview(View v)
    {
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void cellCallBackFromIndex(int index, Object object) {
        //按钮回调
        WorkOrderModel model=historyList.get(index);
        switch (model.workOderStatus)
        {
            case 900002://设备领用
            {
                Intent intent=new Intent(MyWorkOrderActivity.this,DeviceCheckActivity.class);
                intent.putExtra("type",model.workOderStatus);
                intent.putExtra("orderId",model.pid);
                startActivity(intent);
                break;
            }
            case 900003://上单
            {
                currentModel=model;
                locationModel=new LocationModel();
                mLocationClient.start();
                showToast("定位中");

                break;
            }
            case 900005://归还
            {
                Intent intent=new Intent(MyWorkOrderActivity.this,DeviceCheckActivity.class);
                intent.putExtra("type",model.workOderStatus);
                intent.putExtra("orderId",model.pid);
                startActivity(intent);
                break;
            }
            case 900007://移交
            {
                Intent intent=new Intent(MyWorkOrderActivity.this,DeviceCheckActivity.class);
                intent.putExtra("type",model.workOderStatus);
                intent.putExtra("orderId",model.pid);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void bb()
    {
        JsonObject jo=new JsonObject();
        jo.addProperty("workId",PubFunction.userid);
        jo.addProperty("longitude",0.0f);
        jo.addProperty("latitude",1.1f);
        jo.addProperty("orderId",currentModel.pid);
        if (currentModel!=null) {
            String url="";
            if (currentModel.workOderStatus==900003)
            {
                url=CommonNetString.Single;
            }else {
                url=CommonNetString.PlaceOrder;
            }

            sendPost(url, jo, "上单中...", 300);
            mLocationClient.stop();
        }
    }
}
