package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class MedicalExaminationModel extends BaseModel {
    public int pid=0          ;//| INT(10)       | NO   | PRI |         | 体检
    public int userId=0      ;//| INT(10)       | YES  |     |         | 用户id
    public String MedicalOrga="" ;//| VARCHAR(45)   | YES  |     |         | 医疗机构
    public String CheckObject="" ;//| VARCHAR(2048) | YES  |     |         | 检查科目
    public String CheckResual="" ;//| VARCHAR(45)   | YES  |     |         | 检查结果
    public String CheckDate=""   ;//| DATE(10)      | YES  |     |         | 体检时间
    public String createDate=""  ;//| DATETIME(19)  | YES  |     |         | 录入时间
    public String checkImg=""    ;//| VARCHAR(45)   | YES  |     |         | 体检结果照片

     public MedicalExaminationModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"MedicalOrga"))
        {
            MedicalOrga=json.get("MedicalOrga").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CheckObject"))
        {
            CheckObject=json.get("CheckObject").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CheckResual"))
        {
            CheckResual=json.get("CheckResual").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"CheckDate"))
        {
            CheckDate=json.get("CheckDate").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"createDate"))
        {
            createDate=json.get("createDate").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"checkImg"))
        {
            checkImg=json.get("checkImg").getAsString();
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
        if (MedicalOrga!=null)
        {
            json.addProperty("MedicalOrga",MedicalOrga);
        }
        if (CheckObject!=null)
        {
            json.addProperty("CheckObject",CheckObject);
        }
        if (CheckResual!=null)
        {
            json.addProperty("CheckResual",CheckResual);
        }
        if (!CheckDate.equals(""))
        {
            json.addProperty("CheckDate",CheckDate);
        }
        if (!createDate.equals(""))
        {
            json.addProperty("createDate",createDate);
        }
        if (checkImg!=null)
        {
            json.addProperty("checkImg",checkImg);
        }
        return json;
    }
}
