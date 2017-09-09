package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/2/9.
 * Table: NeedsList
 -----------------+---------------+------+-----+---------+---------
 Field           | Type          | Null | Key | Default | Remarks
 -----------------+---------------+------+-----+---------+---------
 id              | INT(10)       | NO   | PRI |         | 需求list（首页显示）
 needsListStatus | INT(10)       | YES  |     |         | 需求状态
 serverMoney     | FLOAT(12)     | YES  |     |         | 金额
 serverBonus     | VARCHAR(45)   | YES  |     |         | 奖金范围
 expectedDate    | DATE(10)      | YES  |     |         | 预产期
 currentStatus   | VARCHAR(45)   | YES  |     |         | 当前状态
 serverDuring    | VARCHAR(45)   | YES  |     |         | 服务周期
 serverLevel     | VARCHAR(45)   | YES  |     |         | 要求等级
 serverLanguage  | VARCHAR(45)   | YES  |     |         | 方言
 other           | VARCHAR(2048) | YES  |     |         | 备注
 -----------------+---------------+------+-----+---------+---------
 */

public class NeedInfoModel extends BaseModel {
    public int pid=0;
    public String dueDate="07-10";
    public String dueType="预产期";
    public float price=9000;
    public String moneyType="￥";
    public String priceType="到手价";
    public String city="上海";
    public String days="42天";
    public String grade="特级";
    public String customNeed="干净卫生,勤快,有经验";
    public String customStatus="预产期";
    public String sendDate="2017-4-11";
    public String serverBonus="";
    public int needsListStatus=0;
    public String language="沪语";



    public NeedInfoModel() {
        super();
    }

    public NeedInfoModel(JsonObject json) {
        if(PubFunction.hasJsonObject(json,"id")) pid=json.get("id").getAsInt();
        if (PubFunction.hasJsonObject(json,"currentStatus")) customStatus=json.get("currentStatus").getAsString();
        if (PubFunction.hasJsonObject(json,"expectedDate")) dueDate=json.get("expectedDate").getAsString();
        if (PubFunction.hasJsonObject(json,"serverMoney")) price=json.get("serverMoney").getAsFloat();
        if (PubFunction.hasJsonObject(json,"serverBonus")) serverBonus=json.get("serverBonus").getAsString();
        if (PubFunction.hasJsonObject(json,"serverDuring")) days=json.get("serverDuring").getAsString();
        if (PubFunction.hasJsonObject(json,"serverLevel")) grade=json.get("serverLevel").getAsString();
        if (PubFunction.hasJsonObject(json,"other")) customNeed=json.get("other").getAsString();
        if (PubFunction.hasJsonObject(json,"needsListStatus")) needsListStatus=json.get("needsListStatus").getAsInt();
        if (PubFunction.hasJsonObject(json,"serverLanguage")) language=json.get("serverLanguage").getAsString();
        if (PubFunction.hasJsonObject(json,"city")) city=json.get("city").getAsString();

    }

    @Override
    public JsonObject getJsonObject() {
        return super.getJsonObject();
    }
}
