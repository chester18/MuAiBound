package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;

/**
 * Created by apple on 16/10/17.
 */

public class AnswerQuestionModel extends BaseModel {
    public int pid=0;//             | INT(10)       | NO   | PRI |         | 回答id
    public String result="";//         | VARCHAR(2048) | YES  |     |         | 答案
    public String files=""           ;//| VARCHAR(255)  | YES  |     |         | 附带文件
    public String submitTime=""     ;//| DATETIME(19)  | YES  |     |         | 提交时间
    public String other=""          ;//| VARCHAR(1024) | YES  |     |         | 其他
    public int  AnswerPaperId=-1 ;//| INT(10)       | NO   | PRI | 0       |
    public int QuestionId=-1    ;//| INT(10)       | NO   | PRI | 0       |

    public AnswerQuestionModel(JsonObject json) {
        super(json);
        if (json.has("id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (json.has("result"))
        {
            result=json.get("result").getAsString();
        }
        if (json.has("file"))
        {
            files=json.get("file").getAsString();
        }
        if (json.has("submitTime"))
        {
            submitTime=json.get("submitTime").getAsString();
        }
        if (json.has("other"))
        {
            other=json.get("other").getAsString();
        }
        if (json.has("AnswerPaperId"))
        {
            AnswerPaperId=json.get("AnswerPaperId").getAsInt();
        }
        if (json.has("QuestionId"))
        {
            QuestionId=json.get("QuestionId").getAsInt();
        }
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (result!=null)
        {
            json.addProperty("result",result);
        }
        if (files!=null)
        {
            json.addProperty("file",files);
        }
        if (submitTime!=null)
        {
            json.addProperty("submitTime",submitTime);
        }
        if (other!=null)
        {
            json.addProperty("other",other);
        }
        if (AnswerPaperId!=-1)
        {
            json.addProperty("AnswerPaperId",AnswerPaperId);
        }
        if (QuestionId!=-1)
        {
            json.addProperty("QuestionId",QuestionId);
        }
        return json;
    }
}
