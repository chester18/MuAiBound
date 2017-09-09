package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/7/17.
 */

public class LeaveHistoryModel extends BaseModel {

    public int pid=0;// | INT UNSIGNED(10) | NO | PRI | |
    public int userId=0;// | INT(10) | YES | | | 月嫂id
    public String startTime;// | DATETIME(19) | YES | | | 请假开始时间
    public String endTime;// | DATETIME(19) | YES | | | 请假结束时间
    public String time;// | DATETIME(19) | YES | | | 录入时间
    public int customer=0;// | INT(10) | YES | | | 客户确认状态
    public int manager=0;// | INT(10) | YES | | | 后台确认状态
    public LeaveHistoryModel() {
        super();
    }

    public LeaveHistoryModel(JsonObject json) {
        super(json);
        if(PubFunction.hasJsonObject(json,"id")) pid=json.get("id").getAsInt();
        if(PubFunction.hasJsonObject(json,"userId")) userId=json.get("userId").getAsInt();
        if(PubFunction.hasJsonObject(json,"startTime")) startTime=json.get("startTime").getAsString();
        if(PubFunction.hasJsonObject(json,"endTime")) endTime=json.get("endTime").getAsString();
        if(PubFunction.hasJsonObject(json,"time")) time=json.get("time").getAsString();
        if(PubFunction.hasJsonObject(json,"customer")) customer=json.get("customer").getAsInt();
        if(PubFunction.hasJsonObject(json,"manager")) manager=json.get("manager").getAsInt();


    }

    @Override
    public JsonObject getJsonObject() {

        JsonObject jo=new JsonObject();
        if (pid!=0) jo.addProperty("id",pid);
        if (userId!=0) jo.addProperty("userId",userId);
        if (startTime!=null) jo.addProperty("startTime",startTime);
        if (endTime!=null) jo.addProperty("endTime",endTime);
        if (time!=null) jo.addProperty("time",time);
        if (customer!=0) jo.addProperty("customer",customer);
        if (manager!=0) jo.addProperty("manager",manager);

        return jo;
    }
}
