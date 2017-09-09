package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class HomeMemberModel extends BaseModel {
    public int pid=0         ;//| INT(10)       | NO   | PRI |         | 家庭成员

    public int userId=0     ;//| INT(10)       | YES  |     |         | 月嫂id
    public String MemberName="" ;//| VARCHAR(45)   | YES  |     |         | 姓名
    public String Relation=""   ;//| VARCHAR(45)   | YES  |     |         | 关系
    public String age=""        ;//| VARCHAR(45)   | YES  |     |         | 年龄
    public String work=""       ;//| VARCHAR(1024) | YES  |     |         | 工作单位/职务
    public String mobile=""     ;//| VARCHAR(45)   | YES  |     |         | 联系电话

    public HomeMemberModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"MemberName"))
        {
            MemberName=json.get("MemberName").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"Relation"))
        {
            Relation=json.get("Relation").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"age"))
        {
            age=json.get("age").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"work"))
        {
            work=json.get("work").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"mobile"))
        {
            mobile=json.get("mobile").getAsString();
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
        if (MemberName!=null)
        {
            json.addProperty("MemberName",MemberName);
        }
        if (Relation!=null)
        {
            json.addProperty("Relation",Relation);
        }
        if (age!=null)
        {
            json.addProperty("age",age);
        }
        if (work!=null)
        {
            json.addProperty("work",work);
        }
        if (mobile!=null)
        {
            json.addProperty("mobile",mobile);
        }
        return json;
    }
}
