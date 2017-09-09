package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class TechnicalAbilityModel extends BaseModel {
    public int pid          ;//| INT(10)     | NO   | PRI |         | 技能
    public String userId      ;//| VARCHAR(45) | YES  |     |         |
    public String AbilityName ;//| VARCHAR(45) | YES  |     |         | 技能名称
    public int AbilityType ;//| INT(10)     | YES  |     |         | 技能分类

    public TechnicalAbilityModel(JsonObject json) {

        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"AbilityName"))
        {
            AbilityName=json.get("AbilityName").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"AbilityType"))
        {
            AbilityType=json.get("AbilityType").getAsInt();
        }
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=0)
        {
            json.addProperty("id",pid);
        }
        if (userId!=null)
        {
            json.addProperty("userId",userId);
        }
        if (AbilityName!=null)
        {
            json.addProperty("AbilityName",AbilityName);
        }
        if (AbilityType!=0)
        {
            json.addProperty("AbilityType",AbilityType);
        }

        return json;
    }
}
