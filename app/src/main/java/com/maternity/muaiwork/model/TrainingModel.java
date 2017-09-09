package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class TrainingModel extends BaseModel {
    public int pid               ;//| INT(10)       | NO   | PRI |         | 培训经历
    public int userId           ;//| INT(10)       | YES  |     |         |
    public String TrainingDuring   ;//| VARCHAR(1024) | YES  |     |         | 培训周期
    public String OrganizationName ;//| VARCHAR(1024) | YES  |     |         | 培训机构
    public String TrainingContent  ;//| VARCHAR(45)   | YES  |     |         | 培训名称
    public String TrainingObject   ;//| VARCHAR(2048) | YES  |     |         | 培训科目
    public String TrainingGrade    ;//| VARCHAR(45)   | YES  |     |         | 培训等级
    public String TrainingResult   ;//| VARCHAR(45)   | YES  |     |         | 培训结果（合格或分数）
    public String TrainingImg      ;//| VARCHAR(2048) | YES  |     |         | 证书照片或培训照片

    public TrainingModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"TrainingDuring"))
        {
            TrainingDuring=json.get("TrainingDuring").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"OrganizationName"))
        {
            OrganizationName=json.get("OrganizationName").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"TrainingContent"))
        {
            TrainingContent=json.get("TrainingContent").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"TrainingObject"))
        {
            TrainingObject=json.get("TrainingObject").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"TrainingGrade"))
        {
            TrainingGrade=json.get("TrainingGrade").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"TrainingResult"))
        {
            TrainingResult=json.get("TrainingResult").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"TrainingImg"))
        {
            TrainingImg=json.get("TrainingImg").getAsString();
        }
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=0)
        {
            json.addProperty("id",pid);
        }
        if (userId!=0)
        {
            json.addProperty("userId",userId);
        }
        if (TrainingDuring!=null)
        {
            json.addProperty("TrainingDuring",TrainingDuring);
        }
        if (OrganizationName!=null)
        {
            json.addProperty("OrganizationName",OrganizationName);
        }
        if (TrainingContent!=null)
        {
            json.addProperty("TrainingContent",TrainingContent);
        }
        if (TrainingObject!=null)
        {
            json.addProperty("TrainingObject",TrainingObject);
        }
        if (TrainingGrade!=null)
        {
            json.addProperty("TrainingGrade",TrainingGrade);
        }
        if (TrainingResult!=null)
        {
            json.addProperty("TrainingResult",TrainingResult);
        }
        if (TrainingImg!=null)
        {
            json.addProperty("userName",TrainingImg);
        }

        return json;
    }
}
