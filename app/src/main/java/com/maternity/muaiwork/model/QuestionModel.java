package com.maternity.muaiwork.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/10/17.
 */

public class QuestionModel extends BaseModel {
    public int pid=0               ;//| INT(10)       | NO   | PRI |         |
    public int serial=0           ;//| INT(10)       | YES  |     |         |
    public String content =""         ;//| VARCHAR(2048) | YES  |     |         |
    public String file=""             ;//| VARCHAR(255)  | YES  |     |         |
    public int questionTypeId=0 ;//| INT(10)       ;//| NO   | PRI | 0       | 试题类型
    //（简答、选择、视频J、音频J、图片J、视频X、音频X、图片X）
    public int resultTypeId1=0  ;//| INT(10)       | NO   | PRI | 0       | 结果类型（文本，视频，音频，图片）
    public int PaperId=0         ;//| INT(10)       | NO   | PRI | 0       | 试卷id
    public List<SelectModel> selectList;
    public QuestionModel(JsonObject pjson) {
        JsonObject json=pjson.getAsJsonObject("question");
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"serial"))
        {
            serial=json.get("serial").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"content"))
        {
            content=json.get("content").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"file"))
        {
            file=json.get("file").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"questionTypeId"))
        {
            questionTypeId=json.get("questionTypeId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"resultTypeId1"))
        {
            resultTypeId1=json.get("resultTypeId1").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"PaperId"))
        {
            PaperId=json.get("PaperId").getAsInt();
        }
        if (PubFunction.hasJsonObject(pjson ,"items"))
        {
            JsonArray items=pjson.getAsJsonArray("items");
            if (items.size()>0)
            {
                selectList=new ArrayList<SelectModel>();
                for (int i=0;i<items.size();i++)
                {
                    selectList.add(new SelectModel(items.get(i).getAsJsonObject()));
                }
            }
        }else {
            selectList=new ArrayList<SelectModel>();
        }


    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (serial!=-1)
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
        if (questionTypeId!=-1)
        {
            json.addProperty("questionTypeId",questionTypeId);
        }
        if (resultTypeId1!=-1)
        {
            json.addProperty("resultTypeId1",resultTypeId1);
        }
        if (PaperId!=-1)
        {
            json.addProperty("serial",PaperId);
        }
        return super.getJsonObject();
    }


}
