package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class UserModel extends BaseModel {
    public int pid=0;           //| INT(10)      | NO   | PRI |         | 用户ID
    public String userName="";     //| VARCHAR(45)  | YES  |     |         | 用户名
    public String userPassword=""; //| VARCHAR(45)  | YES  |     |         | 密码

    public int statusId=0;     //| INT(10)      | YES  |     |         | 状态
    public int typeId=0;       //| INT(10)      | YES  |     |         | 用户分类
    public int permissionId=0; //| INT(10)      | YES  |     |         | 用户状态
    public int plantformId=0;  //| INT(10)      | YES  |     |         | 平台id
    public String wxId="";         //| VARCHAR(255) | YES  |     |         | 微信号
    public String qqId="";         //| VARCHAR(255) | YES  |     |         | Quid

    public UserModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userName"))
        {
            userName=json.get("userName").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"userPassword"))
        {
            userPassword=json.get("userPassword").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"typeId"))
        {
            typeId=json.get("typeId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"statusId"))
        {
            statusId=json.get("statusId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"permissionId"))
        {
            permissionId=json.get("permissionId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"plantformId"))
        {
            plantformId=json.get("plantformId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"wxId"))
        {
            wxId=json.get("wxId").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"qqId"))
        {
            qqId=json.get("qqId").getAsString();
        }
    }


    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (userName!=null)
        {
            json.addProperty("userName",userName);
        }
        if (userPassword!=null)
        {
            json.addProperty("userPassword",userPassword);
        }
        if (statusId!=-1)
        {
            json.addProperty("statusId",statusId);
        }
        if (typeId!=-1)
        {
            json.addProperty("typeId",typeId);
        }
        if (permissionId!=-1)
        {
            json.addProperty("permissionId",permissionId);
        }
        if (plantformId!=-1)
        {
            json.addProperty("plantformId",plantformId);
        }
        if (wxId!=null)
        {
            json.addProperty("wxId",wxId);
        }
        if (qqId!=null)
        {
            json.addProperty("qqId",qqId);
        }
        return super.getJsonObject();
    }
}
