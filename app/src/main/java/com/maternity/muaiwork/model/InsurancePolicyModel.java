package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class InsurancePolicyModel extends BaseModel {
    public int pid=0         ;//| INT(10)       | NO   | PRI |         | 保单信息
    public String userId=""     ;//| VARCHAR(45)   | YES  |     |         | 用户id
    public String PolicyName="" ;//| VARCHAR(255)  | YES  |     |         | 保险公司名称
    public String PolicyNo=""   ;//| VARCHAR(45)   | YES  |     |         | 保险单号
    public String PolicyType="" ;//| VARCHAR(1024) | YES  |     |         | 险种
    public String ExpiryDate="" ;//| VARCHAR(45)   | YES  |     |         | 有效期
    public String PolicyImg=""  ;//| VARCHAR(45)   | YES  |     |         | 保险单照片

    public InsurancePolicyModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"PolicyName"))
        {
            PolicyName=json.get("PolicyName").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"PolicyNo"))
        {
            PolicyNo=json.get("PolicyNo").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"PolicyType"))
        {
            PolicyType=json.get("PolicyType").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"ExpiryDate"))
        {
            ExpiryDate=json.get("ExpiryDate").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"PolicyImg"))
        {
            PolicyImg=json.get("PolicyImg").getAsString();
        }

    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (userId!=null)
        {
            json.addProperty("userName",userId);
        }
        if (PolicyName!=null)
        {
            json.addProperty("PolicyName",PolicyName);
        }
        if (PolicyNo!=null)
        {
            json.addProperty("PolicyNo",PolicyNo);
        }
        if (PolicyType!=null)
        {
            json.addProperty("PolicyType",PolicyType);
        }
        if (ExpiryDate!=null)
        {
            json.addProperty("ExpiryDate",ExpiryDate);
        }
        if (PolicyImg!=null)
        {
            json.addProperty("PolicyImg",PolicyImg);
        }
        return json;

    }
}
