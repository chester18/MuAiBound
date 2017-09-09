package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/6/26.
 */

public class ApplyLeaveModel extends BaseModel {
    public int pid=0;
    public int uid=0;
    public String startTime,endTime; //开始，结束时间
    public String reason; //原因
    public String applyTime; //申请时间
    public int cstatus=0; //用户确认
    public int mstatus=0; //后台状态1.批准。2.未批准

    public ApplyLeaveModel() {
        super();
    }

    public ApplyLeaveModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id")) pid=json.get("id").getAsInt();
        if (PubFunction.hasJsonObject(json,"userId")) uid=json.get("userId").getAsInt();
        if (PubFunction.hasJsonObject(json,"startTime")) startTime=json.get("startTime").getAsString();
        if (PubFunction.hasJsonObject(json,"endTime")) endTime=json.get("endTime").getAsString();
        if (PubFunction.hasJsonObject(json,"time")) applyTime=json.get("time").getAsString();
        if (PubFunction.hasJsonObject(json,"manager")) mstatus=json.get("manager").getAsInt();
        if (PubFunction.hasJsonObject(json,"customer")) cstatus=json.get("customer").getAsInt();
        if (PubFunction.hasJsonObject(json,"reason")) reason=json.get("reason").getAsString();
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject jo=new JsonObject();
        if (uid>0)jo.addProperty("userId",uid);
        if (startTime!=null) jo.addProperty("startTime",startTime);
        if (endTime!=null) jo.addProperty("endTime",endTime);
        if (applyTime!=null) jo.addProperty("time",applyTime);
        if (reason!=null) jo.addProperty("reason",reason);
        return jo;
    }
}
