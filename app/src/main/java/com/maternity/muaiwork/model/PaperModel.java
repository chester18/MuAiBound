package com.maternity.muaiwork.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/10/17.
 */

public class PaperModel extends BaseModel {
    public int pid=0          ;//| INT(10)       | NO   | PRI |         |
    public String content=""     ;//| VARCHAR(2048) | YES  |     |         | 内容
    public int UserId=0     ;//| INT(10)       | NO   | PRI | 0       |
    public String title=""       ;//| VARCHAR(255)  | YES  |     |         | 标题
    public String type=""        ;//| VARCHAR(45)   | YES  |     |         | 试卷类型
    public String createTime=""  ;//| DATETIME(19)  | YES  |     |         | 创建时间
    public String changTime=""   ;//| DATETIME(19)  | YES  |     |         | 更新时间
    public String releaseTime="" ;//| DATETIME(19)  | YES  |     |         | 发布时间
    public int StatusId=0   ;//| INT(10)       | NO   | PRI |         |
    public int TypeId=0     ;//| INT(10)       | NO   | PRI |         |

    public List<QuestionModel> questionList;


    public PaperModel(JsonObject pjson) {
        JsonObject json=pjson.getAsJsonObject("paper");

        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"content"))
        {
            content=json.get("content").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"UserId"))
        {
            UserId=json.get("UserId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"title"))
        {
            title=json.get("title").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"type"))
        {
            type=json.get("type").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"createTime"))
        {
            createTime=json.get("createTime").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"changTime"))
        {
            changTime=json.get("changTime").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"releaseTime"))
        {
            releaseTime=json.get("releaseTime").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"StatusId"))
        {
            StatusId=json.get("StatusId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"TypeId"))
        {
            TypeId=json.get("TypeId").getAsInt();
        }
        if(PubFunction.hasJsonObject(pjson,"questions"))
        {
            JsonArray questions=pjson.getAsJsonArray("questions");
            questionList=new ArrayList<QuestionModel>();
            for (int i=0;i<questions.size();i++)
            {
                questionList.add(new QuestionModel(questions.get(i).getAsJsonObject()));
            }
        }

    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject json=new JsonObject();
        if (pid!=-1)
        {
            json.addProperty("id",pid);
        }
        if (content!=null)
        {
            json.addProperty("content",content);
        }
        if (UserId!=-1)
        {
            json.addProperty("id",UserId);
        }
        if (title!=null)
        {
            json.addProperty("title",title);
        }
        if (type!=null)
        {
            json.addProperty("type",type);
        }
        if (createTime!=null)
        {
            json.addProperty("createTime",createTime);
        }
        if (changTime!=null)
        {
            json.addProperty("changTime",changTime);
        }
        if (releaseTime!=null)
        {
            json.addProperty("releaseTime",releaseTime);
        }
        if (StatusId!=-1)
        {
            json.addProperty("StatusId",StatusId);
        }if (TypeId!=-1)
        {
            json.addProperty("TypeId",TypeId);
        }



        return json;
    }
}
