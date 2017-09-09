package com.maternity.muaiwork.dialog;

import android.content.Context;

/**
 * Created by apple on 2016/10/19.
 */

public class DialogFactry {
    public static final int DIA_INPUT=0x10;
    public static final int DIA_DATE=0x20;
    public static final int DIA_IMAGE=0x30;
    public static final int DIA_LIST=0x40;
    public static final int DIA_MULIT=0x50;
    public static final int DIA_TEXT=0x60;
    public static final int DIA_SUCC=0x70;
    public static final int DIA_INFO=0x80;
    public static final String DIA_NULL="";
    public static BaseDialog instance(Context content,int type,int tag,String title,String msg,DialogCallBackInterface callBack){
        BaseDialog basd=null;
        switch (type)
        {
            case DIA_INPUT:
            {
                basd=new InputDialog(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_DATE:
            {
                basd=new DateDialog(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_IMAGE:
            {
                basd=new SelectImageSourceDialog(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_LIST:
            {
                basd=new DialogSimpleList(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_MULIT:
            {
                basd=new MulitSelectDialog(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_TEXT:
            {
                basd=new DialogText(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_SUCC:
            {
                basd=new DialogSuccess(content,title,msg,tag);
                basd.callBack=callBack;
                break;
            }
            case DIA_INFO:
            {
                basd=new DialogInfoText(content,title,msg,tag);
                basd.callBack=callBack;
            }
            default:
                break;
        }

        return basd;
    }
}
