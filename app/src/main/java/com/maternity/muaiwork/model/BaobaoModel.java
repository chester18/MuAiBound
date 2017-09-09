package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/2/16.
 */

public class BaobaoModel extends BaseModel {

    public int bId=0;
    public String baoname="暂无";
    public String weight="暂无";
    public int babatype=0;
    public String outdate="暂无";

    public BaobaoModel() {
        super();
    }

    public BaobaoModel(JsonObject json) {
        super(json);
        if (json!=null)
        {
            if (PubFunction.hasJsonObject(json,"id")) bId=json.get("id").getAsInt();
            if (PubFunction.hasJsonObject(json,"childName")) baoname=json.get("childName").getAsString();
            if (PubFunction.hasJsonObject(json,"childWight")) weight=json.get("childWight").getAsFloat()+"kg";
            if (PubFunction.hasJsonObject(json,"childType"))
            {
                babatype=json.get("childType").getAsInt();

            }
            if (PubFunction.hasJsonObject(json,"brithTime")) outdate=json.get("brithTime").getAsString();
        }
    }
}
