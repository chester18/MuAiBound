package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class PersonVideoModel extends BaseModel {
    public int pid=0        ;//| INT(10)       | NO   | PRI |         | 个人视频
    public int userId=0    ;//| INT(10)       | YES  |     |         | 用户ID
    public String lifeVideo="" ;//| VARCHAR(1024) | YES  |     |         | 生活视频
    public String workVideo="" ;//| VARCHAR(1024) | YES  |     |         | 职业视频

    public PersonVideoModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"lifeVideo"))
        {
            lifeVideo=json.get("lifeVideo").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"workVideo"))
        {
            workVideo=json.get("workVideo").getAsString();
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
        if (lifeVideo!=null)
        {
            json.addProperty("lifeVideo",lifeVideo);
        }
        if (workVideo!=null)
        {
            json.addProperty("workVideo",workVideo);
        }
        return super.getJsonObject();
    }
}
