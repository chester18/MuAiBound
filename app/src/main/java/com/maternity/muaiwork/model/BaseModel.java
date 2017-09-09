package com.maternity.muaiwork.model;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by apple on 16/10/12.
 */

public abstract class BaseModel extends Object implements Serializable {
    int tag;
    public String name;
    public BaseModel()
    {
        tag=0;
    }
    public BaseModel(Object object)
    {
        tag=0;
        name="";
    }
    public BaseModel(JsonObject json)
    {
        tag=0;
        name="";
    }
    public JsonObject getJsonObject()
    {
        return new JsonObject();
    }
}
