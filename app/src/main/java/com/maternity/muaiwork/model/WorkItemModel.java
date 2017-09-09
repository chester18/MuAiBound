package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/7/3.
 */

public class WorkItemModel extends BaseModel {
    public int pid                      ;//| INT(10)       | NO   | PRI |         |
    public int workRecordItemType      ;//| INT(10)       | YES  |     |         | 记录分类
    public String workRecordItemName      ;//| VARCHAR(1024) | YES  |     |         | 记录项名称
    public int workRecordItemValueType ;//| INT(10)       | YES  |     |         | 记录值类型（字符串，数字，单选项，多选项）
    public String workRecordItemSelect    ;

    public WorkItemModel() {
        super();
    }

    public WorkItemModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id")) pid=json.get("id").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordItemType")) workRecordItemType=json.get("workRecordItemType").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordItemName")) workRecordItemName=json.get("workRecordItemName").getAsString();
        if (PubFunction.hasJsonObject(json,"workRecordItemValueType")) workRecordItemValueType=json.get("workRecordItemValueType").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordItemSelect")) workRecordItemSelect=json.get("workRecordItemSelect").getAsString();

    }
}
