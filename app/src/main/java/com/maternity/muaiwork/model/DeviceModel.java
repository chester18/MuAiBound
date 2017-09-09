package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/7/6.
 */

public class DeviceModel extends BaseModel
{
    public int sid=0;
    public String devName="";
    public DeviceModel() {
        super();
    }

    public DeviceModel(JsonObject json) {
        super(json);
        if (json != null) {
            if(PubFunction.hasJsonObject(json,"id")) sid=json.get("id").getAsInt();
            if (PubFunction.hasJsonObject(json,"name")) devName=json.get("name").getAsString();
        }
    }
}
