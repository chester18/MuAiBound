package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/3/9.
 */

public class WorkHistoryModel extends BaseModel {
    public int pid=0                   ;//| INT(10)       | NO   | PRI |         |
    public int orderListId=0          ;//| INT(10)       | YES  |     |         | 订单号
    public int workRecordItemId=0     ;//| INT(10)       | YES  |     |         | 记录项编号
    public String workRecordListValue="" ;//| VARCHAR(2048) | YES  |     |         | 记录结果
    public String workRecordListOther=""  ;//| VARCHAR(2048) | YES  |     |         | 其他备注
    public int workerId=0             ;//| INT(10)       | YES  |     |         | 记录人ID
    public String workRecordListDate=""   ;//| DATETIME(19)  | YES  |     |         | 记录时间
    public String workRecordListDay=""    ;//| DATE(10)      | YES  |     |         | 记录天
    public String workRecordListImages="" ;//| VARCHAR(4095) | YES  |     |         | 照片组
    public int workServerId=0         ;//| INT(10)       | YES  |     | 0       | 记录对象id
    public int workRecordType=0       ;//| INT(10)       | YES  |     | 0       | 记录类型（妈妈，宝宝）
    public String workRecordItemName="";//记录项名称
    public WorkHistoryModel() {
        super();
    }

    public WorkHistoryModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"id")) pid=json.get("id").getAsInt();
        if (PubFunction.hasJsonObject(json,"orderListId")) orderListId=json.get("orderListId").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordItemId")) workRecordItemId=json.get("workRecordItemId").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordListValue")) workRecordListValue=json.get("workRecordListValue").getAsString();
        if (PubFunction.hasJsonObject(json,"workRecordListOther")) workRecordListOther=json.get("workRecordListOther").getAsString();
        if (PubFunction.hasJsonObject(json,"workerId")) workerId=json.get("workerId").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordListDate")) workRecordListDate=json.get("workRecordListDate").getAsString();
        if (PubFunction.hasJsonObject(json,"workRecordListDay")) workRecordListDay=json.get("workRecordListDay").getAsString();
        if (PubFunction.hasJsonObject(json,"workRecordListImages")) workRecordListImages=json.get("workRecordListImages").getAsString();
//        if (PubFunction.hasJsonObject(json,"orderListId")) orderListId=json.get("orderListId").getAsInt();
        if (PubFunction.hasJsonObject(json,"workServerId")) workServerId=json.get("workServerId").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordType")) workRecordType=json.get("workRecordType").getAsInt();
        if (PubFunction.hasJsonObject(json,"workRecordItemName")) workRecordItemName=json.get("workRecordItemName").getAsString();
    }

    @Override
    public JsonObject getJsonObject() {
        JsonObject jsobject=new JsonObject();
        if (pid!=0)jsobject.addProperty("id",pid);
        if (orderListId!=0)jsobject.addProperty("orderListId",orderListId);
        if (workRecordItemId!=0)jsobject.addProperty("workRecordItemId",workRecordItemId);
        if (workRecordListValue!=null)jsobject.addProperty("workRecordListValue",workRecordListValue);
        if (workRecordListOther!=null)jsobject.addProperty("workRecordListOther",workRecordListOther);
        if (workerId!=0)jsobject.addProperty("workerId",workerId);
       // if (workRecordListDate!=null)jsobject.addProperty("workRecordListDate",workRecordListDate);
       // if (workRecordListDay!=null)jsobject.addProperty("workRecordListDay",workRecordListDay);
        if (workRecordListImages!=null)jsobject.addProperty("workRecordListImages",workRecordListImages);
        if (workServerId!=0)jsobject.addProperty("workServerId",workServerId);
        if (workRecordType!=0)jsobject.addProperty("workRecordType",workRecordType);
        if (workRecordItemName!=null)jsobject.addProperty("workRecordItemName",workRecordItemName);

        return jsobject;
    }

    /**
     * 添加图片文件
     * @param fileName
     */
    public void addPhoto(String fileName)
    {
        if ("".equals(workRecordListImages))
        {
            workRecordListImages=fileName;
        }else {
            workRecordListImages=workRecordListImages+","+fileName;
        }
    }
}
