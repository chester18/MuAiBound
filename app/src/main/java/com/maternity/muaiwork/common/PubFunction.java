package com.maternity.muaiwork.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by apple on 16/10/14.
 */

public class PubFunction {
    public static String userName;
    public static int userid;
    public static int useType;
    public static int workOrderId;

    public static void readInfo(Context context) //读取数据
    {
        SharedPreferences share=context.getSharedPreferences("User", Context.MODE_PRIVATE);
        userName=share.getString("username","");
        userid=share.getInt("userid",0);
        useType=share.getInt("userType",0);
        workOrderId=share.getInt("workOrderId",0);
    }
    public static void cleanInfo(Context content)
    {
        userName="";
        useType=0;
        userid=0;
        workOrderId=0;
        saveInfo(content);
    }
    public static void saveInfo(Context context) //保存数据
    {
        SharedPreferences share=context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=share.edit();
        edit.putString("username",userName);
        edit.putInt("userid",userid);
        edit.putInt("userType",useType);
        edit.putInt("workOrderId",workOrderId);
        edit.commit();
    }
    public static boolean hasJsonObject(JsonObject object,String key)
    {
        if (object.has(key))
        {
            if (!object.get(key).isJsonNull())
            {
                return true;
            }
        }
        return false;
    }



    public static String getTodayAtg()
    {
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static String getTodayTime()
    {
        java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ndate=new Date();
        return format.format(ndate);
    }
}
