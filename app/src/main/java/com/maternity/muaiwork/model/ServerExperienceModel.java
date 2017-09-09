package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class ServerExperienceModel extends BaseModel {
    public int pid            ;//| INT(10)       | NO   | PRI |         | 从业履历
    public String userId        ;//| VARCHAR(45)   | YES  |     |         |
    public String ServerObject  ;//| VARCHAR(1024) | YES  |     |         | 服务对象
    public String ServerContent ;//| VARCHAR(2048) | YES  |     |         | 服务内容
    public String ServerDuring  ;//| VARCHAR(1024) | YES  |     |         | 服务时间段
    public String ServerMobile  ;//| VARCHAR(45)   | YES  |     |         | 服务人联系电话

    public ServerExperienceModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"ServerObject"))
        {
            ServerObject=json.get("ServerObject").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"ServerContent"))
        {
            ServerContent=json.get("ServerContent").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"ServerDuring"))
        {
            ServerDuring=json.get("ServerDuring").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"ServerMobile"))
        {
            ServerMobile=json.get("ServerMobile").getAsString();
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
        if (ServerObject!=null)
        {
            json.addProperty("ServerObject",ServerObject);
        }
        if (ServerContent!=null)
        {
            json.addProperty("ServerContent",ServerContent);
        }
        if (ServerDuring!=null)
        {
            json.addProperty("ServerDuring",ServerDuring);
        }
        if (ServerMobile!=null)
        {
            json.addProperty("userName",ServerMobile);
        }
        return json;
    }
}
