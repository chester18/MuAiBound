package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by apple on 2017/7/7.
 * 工单model
 */

public class WorkOrderModel extends BaseModel {

    public int pid=0;// | INT(10) | NO | PRI | | 工单id
    public int oderID=0;// | INT(10) | YES | | | 订单id
    public int momId=0;// | INT(10) | YES | | | 客户id
    public int workId=0;// | INT(10) | YES | | | 月嫂id
    public String momName="";// | VARCHAR(45) | YES | | | 客户姓名
    public String origin="";// | VARCHAR(45) | YES | | | 客户籍贯
    public String phone="";// | VARCHAR(45) | YES | | | 客户手机
    public int project=0;// | INT(10) | YES | | | 服务项目（1.月嫂 2.育婴师）
    public String serverLevel="";// | VARCHAR(45) | YES | | | 服务级别
    public String serverDuring="";// | VARCHAR(45) | YES | | | 服务天数
    public int twoChild=0;// | INT(10) | YES | | | 1.单胎 2.双胎
    public int second=0;// | INT(10) | YES | | | 1.头胎 2.二胎
    public int person=0;// | INT(10) | YES | | | 家中人数
    public String address="";// | VARCHAR(1024) | YES | | | 上岗地址
    public String traffic="";// | VARCHAR(1024) | YES | | | 交通路线
    public String startTime="";// | DATETIME(19) | YES | | | 上岗时间
    public String endTime="";// | DATETIME(19) | YES | | | 下岗时间
    public String startAddress="";// | VARCHAR(1024) | YES | | | 上岗地址
    public String endAddress="";// | VARCHAR(1024) | YES | | | 下岗地址
    public int toolStatus=0;// | INT(10) | YES | | | 设备领用状态
    public int total=0;// | INT(10) | YES | | | 用户评分总分
    public int workOderStatus=0;
    /*
    工单状态（900001, 'WORKODERSTATUS', '派单中', '工单状态'),
                                                              	 (900002, 'WORKODERSTATUS', '设备领用中', '工单状态'),
                                                              	 (900003, 'WORKODERSTATUS', '上单中', '工单状态'),
                                                              	 (900004, 'WORKODERSTATUS', '工作中', '工单状态'),
                                                              	 (900005, 'WORKODERSTATUS', '设备归还中', '工单状态'),
                                                              	 (900006, 'WORKODERSTATUS', '工单完成', '工单状态'),
                                                              	 (900007, 'WORKODERSTATUS', '设备移交中', '工单状态'),
                                                              	 (900008, 'WORKODERSTATUS', '工单失败', '工单状态'））
     */
    public Map<String, String> getDictionary()
    {
        Map<String,String>dictionary=new HashMap<String, String>();
        dictionary.put("客户姓名:",momName);
        dictionary.put("客户籍贯:",origin);
        dictionary.put("手机号:",phone);
        dictionary.put("服务项目:",project==1?"月嫂":"育婴师");
        dictionary.put("服务等级:",serverLevel);
        dictionary.put("服务周期:",serverDuring);
        dictionary.put("上岗地址:",address);
        dictionary.put("交通线路:",traffic);
        return dictionary;
    }
    public WorkOrderModel() {
        super();
    }

    public WorkOrderModel(JsonObject json) {
        super(json);
        if(PubFunction.hasJsonObject(json,"id")) pid=json.get("id").getAsInt();
        if(PubFunction.hasJsonObject(json,"oderID")) oderID=json.get("oderID").getAsInt();
        if(PubFunction.hasJsonObject(json,"momId")) momId=json.get("momId").getAsInt();
        if(PubFunction.hasJsonObject(json,"workId")) workId=json.get("workId").getAsInt();
        if(PubFunction.hasJsonObject(json,"momName")) momName=json.get("momName").getAsString();
        if(PubFunction.hasJsonObject(json,"origin")) origin=json.get("origin").getAsString();
        if(PubFunction.hasJsonObject(json,"phone")) phone=json.get("phone").getAsString();
        if(PubFunction.hasJsonObject(json,"project")) project=json.get("project").getAsInt();
        if(PubFunction.hasJsonObject(json,"serverLevel")) serverLevel=json.get("serverLevel").getAsString();
        if(PubFunction.hasJsonObject(json,"serverDuring")) serverDuring=json.get("serverDuring").getAsString();
        if(PubFunction.hasJsonObject(json,"twoChild")) twoChild=json.get("twoChild").getAsInt();
        if(PubFunction.hasJsonObject(json,"second")) second=json.get("second").getAsInt();
        if(PubFunction.hasJsonObject(json,"person")) person=json.get("person").getAsInt();

        if(PubFunction.hasJsonObject(json,"address")) address=json.get("address").getAsString();
        if(PubFunction.hasJsonObject(json,"traffic")) traffic=json.get("traffic").getAsString();
        if(PubFunction.hasJsonObject(json,"startTime")) startTime=json.get("startTime").getAsString();
        if(PubFunction.hasJsonObject(json,"endTime")) endTime=json.get("endTime").getAsString();
        if(PubFunction.hasJsonObject(json,"startAddress")) startAddress=json.get("startAddress").getAsString();
        if(PubFunction.hasJsonObject(json,"endAddress")) endAddress=json.get("endAddress").getAsString();

        if(PubFunction.hasJsonObject(json,"toolStatus")) toolStatus=json.get("toolStatus").getAsInt();
        if(PubFunction.hasJsonObject(json,"total")) total=json.get("total").getAsInt();
        if(PubFunction.hasJsonObject(json,"workOderStatus")) workOderStatus=json.get("workOderStatus").getAsInt();

    }


}
