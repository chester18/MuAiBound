package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 16/10/17.
 */

public class TrueInfoModel extends BaseModel {
    public int pid=0             ;//| INT(10)       | NO   | PRI |         | 真实信息
    public int userId=0         ;//| INT(10)       | YES  |     |         | 用户id
    public String xingmin=""        ;//| VARCHAR(45)   | YES  |     |         | 姓名
    public String cardId=""         ;//| VARCHAR(45)   | YES  |     |         | 身份证
    public String cardIdImg=""      ;//| VARCHAR(1024) | YES  |     |         | 身份证照片
    public String mobile =""        ;//| VARCHAR(45)   | YES  |     |         | 手机
    public String birthday=""       ;//| DATE(10)      | YES  |     |         | 出生年月
    public int age=0            ;//| INT(10)       | YES  |     |         | 年龄
    public String jiguan=""         ;//| VARCHAR(45)   | YES  |     |         | 籍贯
    public String shuxiang=""       ;//| VARCHAR(5)    | YES  |     |         | 属相
    public float hight=0          ;//| FLOAT(12)     | YES  |     |         | 身高
    public String study=""          ;//| VARCHAR(45)   | YES  |     |         | 学历
    public String site=""           ;//| VARCHAR(45)   | YES  |     |         | 岗位
    public String workExperience="" ;//| VARCHAR(45)   | YES  |     |         | 工作经验
    public String spect=""          ;//| VARCHAR(2048) | YES  |     |         | 特殊技能
    public String salary=""         ;//| VARCHAR(45)   | YES  |     |         | 薪水
    public String talkAbality=""    ;//| VARCHAR(45)   | YES  |     |         | 沟通能力
    public String face=""           ;//| VARCHAR(45)   | YES  |     |         | 面相
    public String language=""       ;//| VARCHAR(45)   | YES  |     |         | 普通话
    public String etiquette=""      ;//| VARCHAR(45)   | YES  |     |         | 礼仪
    public float putotal=0          ;//| FLOAT(12)     | YES  |     |         | 总分
    public String other =""         ;//| VARCHAR(2018) | YES  |     |         | 备注
    public int isverifyStatus=0 ;//| INT(10)       | YES  |     | 400001  | 审核状态
    public String createDate=""     ;//| DATETIME(19)  | YES  |     |         | 创建日期
    public String changDate=""      ;//| DATETIME(19)  | YES  |     |         | 修改日期

    public int homeCount=0;         //服务家庭数量
    public String married="";//婚育状况
    public String city="上海";//所在城市“上海”
    public String certificate="";//证书
    public String tech="";//技能
    public TrueInfoModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id"))
        {
            pid=json.get("id").getAsInt();
        }else {
            pid=0;
        }
        if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }else {

        }
        if (PubFunction.hasJsonObject(json,"xingmin"))
        {
            xingmin=json.get("xingmin").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"cardId"))
        {
            cardId=json.get("cardId").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"cardIdImg"))
        {
            cardIdImg=json.get("cardIdImg").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"mobile"))
        {
            mobile=json.get("mobile").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"birthday"))
        {
            birthday=json.get("birthday").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"age"))
        {
            age=json.get("age").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"jiguan"))
        {
            jiguan=json.get("jiguan").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"shuxiang"))
        {
            shuxiang=json.get("shuxiang").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"hight"))
        {
            hight=json.get("hight").getAsFloat();
        }
        if (PubFunction.hasJsonObject(json,"study"))
        {
            study=json.get("study").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"site"))
        {
            site=json.get("site").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"workExperience"))
        {
            workExperience=json.get("workExperience").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"spect"))
        {
            spect=json.get("spect").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"salary"))
        {
            salary=json.get("salary").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"talkAbality"))
        {
            talkAbality=json.get("talkAbality").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"face"))
        {
            face=json.get("face").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"language"))
        {
            language=json.get("language").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"etiquette"))
        {
            etiquette=json.get("etiquette").getAsString();
        }
        if (PubFunction.hasJsonObject(json,"putotal"))
        {
            putotal=json.get("putotal").getAsFloat();
        }
        if (PubFunction.hasJsonObject(json,"other"))
        {
            other=json.get("other").getAsString();
        }if (PubFunction.hasJsonObject(json,"userId"))
        {
            userId=json.get("userId").getAsInt();
        }
        if (PubFunction.hasJsonObject(json,"isverifyStatus"))
        {
            isverifyStatus=json.get("isverifyStatus").getAsInt();
        }
        if(PubFunction.hasJsonObject(json,"createDate")) {
            createDate = json.get("createDate").getAsString();
        }
        if(PubFunction.hasJsonObject(json,"changDate"))
            changDate=json.get("changDate").getAsString();

        if (PubFunction.hasJsonObject(json,"homeCount"))
            homeCount=json.get("homeCount").getAsInt();
        if (PubFunction.hasJsonObject(json,"married"))
            married=json.get("married").getAsString();
        if (PubFunction.hasJsonObject(json,"city"))
            city=json.get("city").getAsString();
        if (PubFunction.hasJsonObject(json,"certificate"))
            certificate=json.get("certificate").getAsString();
        if (PubFunction.hasJsonObject(json,"tech"))
            tech=json.get("tech").getAsString();
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
        if (xingmin!=null)
        {
            json.addProperty("xingmin",xingmin);
        }
        if (cardId!=null)
        {
            json.addProperty("cardId",cardId);
        }
        if (cardIdImg!=null)
        {
            json.addProperty("cardIdImg",cardIdImg);
        }
        if (mobile!=null)
        {
            json.addProperty("mobile",mobile);
        }
        if (birthday!=null)
        {
            json.addProperty("birthday",birthday);
        }
        if (age!=-1)
        {
            json.addProperty("age",age);
        }
        if (jiguan!=null)
        {
            json.addProperty("jiguan",jiguan);
        }
        if (shuxiang!=null)
        {
            json.addProperty("shuxiang",shuxiang);
        }
        if (hight!=-1)
        {
            json.addProperty("hight",hight);
        }
        if (study!=null)
        {
            json.addProperty("study",study);
        }
        if (site!=null)
        {
            json.addProperty("site",site);
        }
        if (workExperience!=null)
        {
            json.addProperty("workExperience",workExperience);
        }
        if (spect!=null)
        {
            json.addProperty("spect",spect);
        }
        if (salary!=null)
        {
            json.addProperty("salary",salary);
        }
        if (talkAbality!=null)
        {
            json.addProperty("talkAbality",talkAbality);
        }
        if (face!=null)
        {
            json.addProperty("face",face);
        }
        if (language!=null)
        {
            json.addProperty("language",language);
        }
        if (etiquette!=null)
        {
            json.addProperty("etiquette",etiquette);
        }
        if (putotal!=0)
        {
            json.addProperty("putotal",putotal);
        }
        if (other!=null)
        {
            json.addProperty("other",other);
        }
        if (isverifyStatus!=0)
        {
            json.addProperty("isverifyStatus",isverifyStatus);
        }
        if (!createDate.equals(""))
        {
            json.addProperty("createDate",createDate);
        }
        if (!changDate.equals(""))
        {
            json.addProperty("changDate",changDate);
        }

        if (!tech.equals(""))
        {
            json.addProperty("tech",tech);
        }
        if (!certificate.equals(""))
        {
            json.addProperty("certificate",certificate);
        }
        if (!city.equals(""))
        {
            json.addProperty("city",city);
        }
        if (!married.equals(""))
        {
            json.addProperty("married",married);
        }
        if (homeCount!=0)
        {
            json.addProperty("homeCount",homeCount);
        }
        return json;
    }
}
