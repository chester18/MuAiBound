package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

import java.util.List;

/**
 * Created by apple on 16/10/17.
 */

public class SelectModel extends BaseModel {
    public int pid           ;//| INT(10)       | NO   | PRI |         |
    public String serial       ;//| VARCHAR(6)    | YES  |     |         | 顺序
    public String content      ;//| VARCHAR(2048) | YES  |     |         | 选题
    public String file         ;//| VARCHAR(255)  | YES  |     |         | 包含文件
    public String command      ;//| VARCHAR(255)  | YES  |     |         | 操作指令【comm parm】
    public int QuestionId  ;//| INT(10)       | NO   | PRI | 0       |
    public int fileTypeId ;//| INT(10)       | NO   | PRI | 0       |
    public String selectDo     ;//| VARCHAR(1024) | YES  |     |         | 选择后执行命令

    public SelectModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"serial"))
        {
            serial=json.get("serial").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"content"))
        {
            content=json.get("content").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"file"))
        {
            file=json.get("file").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"command"))
        {
            command=json.get("command").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"QuestionId"))
        {
            QuestionId=json.get("QuestionId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"fileTypeId"))
        {
            fileTypeId=json.get("fileTypeId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"selectDo"))
        {
            selectDo=json.get("selectDo").getAsString();
        }


    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=0)
        {
            json.addProperty("id",pid);
        }
        if (serial!=null)
        {
            json.addProperty("serial",serial);
        }
        if (content!=null)
        {
            json.addProperty("content",content);
        }
        if (file!=null)
        {
            json.addProperty("file",file);
        }
        if (command!=null)
        {
            json.addProperty("command",command);
        }
        if (QuestionId!=0)
        {
            json.addProperty("QuestionId",QuestionId);
        }
        if (fileTypeId!=0)
        {
            json.addProperty("fileTypeId",fileTypeId);
        }
        if (selectDo!=null)
        {
            json.addProperty("selectDo",selectDo);
        }
        return json;
    }
}
