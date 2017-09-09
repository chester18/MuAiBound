package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/2/16.
 */

public class MamaModel extends BaseModel {
    public int mid=0;//妈妈ID
    public String mamaname="暂无";//姓名
    public String hospital="暂无";//医院
    public String mode="暂无";//方式
    public String outdate="暂无";//产子时间
    public MamaModel() {
        super();
    }

    public MamaModel(JsonObject json) {
        super(json);
        if (json!=null)
        {
            if (PubFunction.hasJsonObject(json,"id"))  mid=json.get("id").getAsInt();
            if (PubFunction.hasJsonObject(json,"name")) mamaname=json.get("name").getAsString();
            if (PubFunction.hasJsonObject(json,"Hospital")) hospital=json.get("Hospital").getAsString();
            if (PubFunction.hasJsonObject(json,"birthTime")) outdate=json.get("birthTime").getAsString();
            if (PubFunction.hasJsonObject(json,"birthMode"))
            {
                int itd=json.get("birthMode").getAsInt();
                if (itd==1)
                {
                    mode="顺产";
                }else {
                    mode="剖腹产";
                }
            }
        }

    }
}
