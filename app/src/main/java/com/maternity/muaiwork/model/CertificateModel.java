package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class CertificateModel extends BaseModel {
    public int pid;//                    | INT(10)     | NO   | PRI |         | 证书

    public int userId =0               ;//| INT(10)     | YES  |     |         | 用户id
    public String Organization=""          ;//| VARCHAR(45) | YES  |     |         | 发证机构
    public String CertificateName =""      ;//| VARCHAR(45) | YES  |     |         | 证书名称
    public String CertificateGrade =""     ;//| VARCHAR(45) | YES  |     |         | 等级
    public String CertificateDate=""       ;//| VARCHAR(45) | YES  |     |         | 发证日期
    public String CertificateFaceImage=""  ;//| VARCHAR(45) | YES  |     |         | 正面照
    public String CertificateContentImg="" ;//| VARCHAR(45) | YES  |     |         | 内容照

    public CertificateModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"Organization"))
        {
            Organization=json.get("Organization").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CertificateName"))
        {
            CertificateName=json.get("CertificateName").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CertificateGrade"))
        {
            CertificateGrade=json.get("CertificateGrade").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CertificateDate"))
        {
            CertificateDate=json.get("CertificateDate").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CertificateFaceImage"))
        {
            CertificateFaceImage=json.get("CertificateFaceImage").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CertificateContentImg"))
        {
            CertificateContentImg=json.get("CertificateContentImg").getAsString();
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
        if (Organization!=null)
        {
            json.addProperty("Organization",Organization);
        }
        if (CertificateName!=null)
        {
            json.addProperty("CertificateName",CertificateName);
        }
        if (CertificateGrade!=null)
        {
            json.addProperty("CertificateGrade",CertificateGrade);
        }
        if (CertificateDate!=null)
        {
            json.addProperty("CertificateDate",CertificateDate);
        }
        if (CertificateFaceImage!=null)
        {
            json.addProperty("CertificateFaceImage",CertificateFaceImage);
        }
        if (CertificateContentImg!=null)
        {
            json.addProperty("CertificateContentImg",CertificateContentImg);
        }
        return json;
    }
}
