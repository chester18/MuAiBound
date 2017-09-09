package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;

/**
 * Created by apple on 16/10/17.
 */

public class CustomEvaluateModel extends BaseModel {
    public int pid=0            ;//| INT(10)       | NO   | PRI |         | 客户评分

    public int userId =0      ;//| INT(10)       | YES  |     |         | 月嫂用户id
    public String createDate=""    ;//| DATETIME(19)  | YES  |     |         | 创建日期
    public String ServerBearing="" ;//| VARCHAR(45)   | YES  |     |         | 服务态度
    public String ServerCase=""    ;//| VARCHAR(45)   | YES  |     |         | 服务情况
    public String ServerDinner=""  ;//| VARCHAR(45)   | YES  |     |         | 月子正餐、辅餐情况
    public String profession=""    ;//| VARCHAR(45)   | YES  |     |         | 专业度
    public int total=0         ;//| INT(10)       | YES  |     |         | 总分
    public int customId=0      ;//| INT(10)       | YES  |     |         | 客户id
    public String mobile=""        ;//| VARCHAR(45)   | YES  |     |         | 客户电话
    public String ServerDuring=""  ;//| VARCHAR(1024) | YES  |     |         | 服务时间

    CustomEvaluateModel(JsonObject json) {
        super(json);
        if (json.has("id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (json.has("userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (json.has("createDate"))
        {
            createDate=json.get("createDate").getAsString();
        }
        if (json.has("ServerBearing"))
        {
            ServerBearing=json.get("ServerBearing").getAsString();
        }
        if (json.has("ServerCase"))
        {
            ServerCase=json.get("ServerCase").getAsString();
        }
        if (json.has("ServerDinner"))
        {
            ServerDinner=json.get("ServerDinner").getAsString();
        }
        if (json.has("profession"))
        {
            profession=json.get("profession").getAsString();
        }
        if (json.has("total"))
        {
            total=json.get("total").getAsInt();
        }
        if (json.has("customId"))
        {
            customId=json.get("customId").getAsInt();
        }
        if (json.has("mobile"))
        {
            mobile=json.get("mobile").getAsString();
        }
        if (json.has("ServerDuring"))
        {
            ServerDuring=json.get("ServerDuring").getAsString();
        }
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (userId!=-1)
        {
            json.addProperty("userId",userId);
        }
        if (createDate!=null)
        {
            json.addProperty("createDate",createDate);
        }
        if (ServerBearing!=null)
        {
            json.addProperty("ServerBearing",ServerBearing);
        }
        if (ServerCase!=null)
        {
            json.addProperty("ServerCase",ServerCase);
        }
        if (ServerDinner!=null)
        {
            json.addProperty("ServerDinner",ServerDinner);
        }
        if (profession!=null)
        {
            json.addProperty("profession",profession);
        }
        if (total!=-1)
        {
            json.addProperty("total",total);
        }
        if (customId!=-1)
        {
            json.addProperty("customId",customId);
        }
        if (mobile!=null)
        {
            json.addProperty("mobile",mobile);
        }
        if (ServerDuring!=null)
        {
            json.addProperty("ServerDuring",ServerDuring);
        }
        return json;
    }
}
