package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;

/**
 * Created by apple on 16/10/17.
 */

public class AnswerPaperModel extends BaseModel {
    public int pid=0;//         | INT(10)      | NO   | PRI |         |
    public String createDate="";// | DATETIME(19) | YES  |     |         | 创建时间
    public String submitDate=""; //| DATETIME(19) | YES  |     |         | 提交时间
    public String command="";    //| VARCHAR(255) | YES  |     | empty   | 通过后执行指令
    public int UserId=-1;    //| INT(10)      | NO   | PRI | 0       | 答题用户号
    public int PaperId=-1;   //| INT(10)      | NO   | PRI | 0       | 试卷号
    public int StatusId=-1;  //| INT(10)      | NO   | PRI | 0       | 状态号
    public boolean hasReport=false;  //| BIT          | YES  |     | 0       | 是否统计到总分

    AnswerPaperModel(JsonObject json) {
        super(json);
        if (json.has("id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (json.has("createDate"))
        {
            createDate=json.get("createDate").getAsString();
        }
        if (json.has("submitDate"))
        {
            submitDate=json.get("submitDate").getAsString();
        }
        if (json.has("command"))
        {
            command=json.get("command").getAsString();
        }
        if (json.has("UserId"))
        {
            UserId=json.get("UserId").getAsInt();
        }
        if (json.has("PaperId"))
        {
            PaperId=json.get("PaperId").getAsInt();
        }
        if (json.has("StatusId"))
        {
            StatusId=json.get("StatusId").getAsInt();
        }
        if (json.has("hasReport"))
        {
            hasReport=json.get("id").getAsBoolean();
        }
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (createDate!=null)
        {
            json.addProperty("createDate",createDate);
        }
        if (submitDate!=null)
        {
            json.addProperty("submitDate",submitDate);
        }
        if (command!=null)
        {
            json.addProperty("command",command);
        }
        if (UserId!=-1)
        {
            json.addProperty("UserId",UserId);
        }
        if (PaperId!=-1)
        {
            json.addProperty("PaperId",PaperId);
        }
        if (StatusId!=-1)
        {
            json.addProperty("StatusId",StatusId);
        }

            json.addProperty("hasReport",hasReport);

        return json;
    }
}
