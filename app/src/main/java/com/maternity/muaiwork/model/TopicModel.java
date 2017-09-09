package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;
import com.maternity.muaiwork.common.PubFunction;

/**
 * Created by apple on 2017/3/1.
 */

public class TopicModel extends BaseModel {
    public String topicName="好好学习";
    public String topicDate="2017-11-11";
public String topicListImage;
    public String topicListUrl;
    public String topicListCreate;
    public String topicListUpdate;
    public String topicListTitle;
    public TopicModel(JsonObject json) {
        super(json);
        if (PubFunction.hasJsonObject(json,"topicListImage")) topicListImage=json.get("topicListImage").getAsString();
        if (PubFunction.hasJsonObject(json,"topicListUrl")) topicListUrl=json.get("topicListUrl").getAsString();
        if (PubFunction.hasJsonObject(json,"topicListUpdate")) {
            topicListUpdate=json.get("topicListUpdate").getAsString();
            topicDate=topicListUpdate;
        }
        if (PubFunction.hasJsonObject(json,"topicListTitle")){
            topicListTitle=json.get("topicListTitle").getAsString();
            topicName=topicListTitle;
        }
        if (PubFunction.hasJsonObject(json,"topicListCreate")) topicListCreate=json.get("topicListCreate").getAsString();

    }

    public TopicModel() {
        super();
    }
}
